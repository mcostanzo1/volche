package com.administracion.volche.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.administracion.volche.dao.UserRepository;
import com.administracion.volche.domain.User;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import javax.sql.DataSource;
import org.springframework.session.web.http.*;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM="MY_TEST_REALM";

    @Autowired
    DataSource dataSource;


    @Autowired
    private UserRepository userRepository;







    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder( passwordEncoder() ).dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username,role from user where username=?");

    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/**").hasRole("ADMIN")
//				.antMatchers("/tracker/**").hasAnyRole("ADMIN","USER")
//				.antMatchers("/reportes/**").hasAnyRole("ADMIN","USER")
		        .antMatchers("/start").permitAll()
				.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.formLogin()
				.loginPage("/login").failureUrl("/login?error").
				loginProcessingUrl("/loginFormAction").
				usernameParameter("username").
				passwordParameter("password").
				defaultSuccessUrl("/start").
				//permitAll().
				and()
				.requestCache()
				.requestCache(new NullRequestCache());
	}


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/almreport/defectos_diferidos").permitAll()
//                //.antMatchers("/projects/**").authenticated()
//                //.antMatchers("/user/**").hasRole("ADMIN")
//                .antMatchers("/console/**").permitAll().and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login?error").
//                loginProcessingUrl("/loginFormAction").
//                usernameParameter("username").
//                passwordParameter("password").
//                defaultSuccessUrl("/start", true).
//                permitAll().
//                and()
//                .logout().permitAll();
//    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}

package com.administracion.volche.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import com.administracion.volche.dao.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import javax.sql.DataSource;
import org.springframework.session.web.http.*;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM="MY_TEST_REALM";

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


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
        System.out.println(auth);
        auth.jdbcAuthentication().passwordEncoder( passwordEncoder() ).dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username,role from user where username=?");
        System.out.println(auth);
    }



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/**").hasAuthority( "ADMIN" )
                .antMatchers("/edificio/**").hasAuthority( "ADMIN" )
                .antMatchers( "/incidencia/**" ).authenticated()
                .antMatchers( "/presupuesto/**" ).authenticated()
		        .antMatchers("/start").permitAll()
				.and().httpBasic().realmName(REALM)
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

package com.administracion.volche;

import com.administracion.volche.dao.*;
import com.administracion.volche.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class DbSeeder implements CommandLineRunner {

	/**
	 *
	 */
	private UserRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;




	public DbSeeder(UserRepository usersRepository) {
		this.usersRepository = usersRepository;


    }

	@Override
	public void run(String... arg0) {

        usersRepository.deleteAll();



		User user = new User();
		
		user.setUsername("marian@marian.com");
		user.setPassword(passwordEncoder.encode( "marian" ));//user.setPassword(passwordEncoder.encode( "marian" ));
		user.setFirstname("Marian");
		user.setLastname("Costanzo");
		user.setRole("hawai");
        user.setEdificio( "administracion" );
        user.setEnabled( true );

		usersRepository.save(user);
		
		user = new User();
		user.setUsername("flor@flor.com");
		user.setPassword(passwordEncoder.encode( "flor" ));//user.setPassword(passwordEncoder.encode( "flor" ));
		user.setFirstname("flor");
		user.setLastname("Pitrelli");
		user.setRole("ADMIN");
		user.setEdificio( "administracion" );
		user.setEnabled( true );


		usersRepository.save(user);

	}
}

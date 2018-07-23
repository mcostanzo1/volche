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

	private EdificeRepository edificeRepository;


	public DbSeeder(UserRepository usersRepository, EdificeRepository edificeRepository) {
		this.usersRepository = usersRepository;
		this.edificeRepository = edificeRepository;


    }

	@Override
	public void run(String... arg0) {

        usersRepository.deleteAll();
        edificeRepository.deleteAll();

        Edificio edificio = new Edificio();
        edificio.setDireccion( "Av. Boyacá 473" );
        edificio.setPileta( true );
        edificio.setSum( true );
        edificio.setUnidades_funcionales( 48 );
        edificio.setEnabled( true );
        edificeRepository.save( edificio );

		Edificio edificio2 = new Edificio();
		edificio2.setDireccion( "Granaderos 683" );
		edificio2.setPileta( true );
		edificio2.setSum( true );
		edificio2.setUnidades_funcionales( 30 );
		edificio2.setEnabled( true );
		edificeRepository.save( edificio2 );




		User user = new User();
		user.setUsername("marian@marian.com");
		user.setPassword(passwordEncoder.encode( "marian" ));
		user.setFirstname("Marian");
		user.setLastname("Costanzo");
		user.setRole("ADMIN");
        user.setEdificio( "administracion" );
        user.setEnabled( true );
		usersRepository.save(user);
		
		user = new User();
		user.setUsername("flor@flor.com");
		user.setPassword(passwordEncoder.encode( "flor" ));
		user.setFirstname("flor");
		user.setLastname("Pitrelli");
		user.setRole("ADMIN");
		user.setEdificio( "administracion" );
		user.setEnabled( true );
		usersRepository.save(user);

	}
}

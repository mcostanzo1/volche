package com.administracion.volche;

import com.administracion.volche.dao.*;
import com.administracion.volche.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DbSeeder implements CommandLineRunner {

	/**
	 *
	 */
	private UserRepository usersRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;


	private EdificeRepository edificeRepository;
	private IncidenciaRepository incidenciaRepository;
	private CajaRepository cajaRepository;



	public DbSeeder(UserRepository usersRepository, EdificeRepository edificeRepository, IncidenciaRepository incidenciaRepository,
					CajaRepository cajaRepository) {
		this.usersRepository = usersRepository;
		this.edificeRepository = edificeRepository;
		this.incidenciaRepository = incidenciaRepository;
		this.cajaRepository = cajaRepository;


    }

	@Override
	public void run(String... arg0) {

        usersRepository.deleteAll();
        edificeRepository.deleteAll();
        incidenciaRepository.deleteAll(  );
        cajaRepository.deleteAll(  );

        Edificio edificio2 = new Edificio();
		edificio2.setDireccion( "Av. Boyacá 473" );
		edificio2.setPileta( true );
		edificio2.setSum( true );
		edificio2.setUnidades_funcionales( 48 );
		edificio2.setEnabled( true );
		edificeRepository.save( edificio2 );

		edificio2 = new Edificio();
		edificio2.setDireccion( "Granaderos 683" );
		edificio2.setPileta( true );
		edificio2.setSum( true );
		edificio2.setUnidades_funcionales( 30 );
		edificio2.setEnabled( true );
		edificeRepository.save( edificio2 );

		edificio2 = new Edificio();
		edificio2.setDireccion( "Gascon 141" );
		edificio2.setPileta( false );
		edificio2.setSum( false );
		edificio2.setUnidades_funcionales( 35 );
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

		Incidencia incidencia = new Incidencia();
		incidencia.setTipo( "gas" );
		incidencia.setTitulo( "escape de gas" );
		incidencia.setDescripcion( "posible escape de gas" );
		incidencia.setEtapa( "A" );
		incidencia.setEmergencia( true);
		incidencia.setFinalizada( false );
		incidencia.setAprobada( false );
		incidencia.setEdificioid( 3 );
		incidencia.setUsername( "flor@flor.com" );
		incidenciaRepository.save( incidencia );

		incidencia = new Incidencia();
		incidencia.setTipo( "plaga" );
		incidencia.setTitulo( "cucarachas" );
		incidencia.setDescripcion( "hay una invasion de cucarachas " );
		incidencia.setEtapa( "A" );
		incidencia.setEmergencia( true);
		incidencia.setFinalizada( false );
		incidencia.setAprobada( false );
		incidencia.setEdificioid( 3 );
		incidencia.setUsername( "flor" );
		incidenciaRepository.save( incidencia );

		incidencia = new Incidencia();
		incidencia.setTipo( "edilicio" );
		incidencia.setTitulo( "Se termino de romper la parrilla" );
		incidencia.setDescripcion( "La parrilla estaba media rota y ahora se termino de romper" );
		incidencia.setEtapa( "A" );
		incidencia.setEmergencia( true);
		incidencia.setFinalizada( false );
		incidencia.setEdificioid( 1 );
		incidencia.setAprobada( false );
		incidencia.setUsername( "marian@marian.com" );
		incidenciaRepository.save( incidencia );

		Caja caja = new Caja();
		caja.setEdificioid( 1 );
		caja.setBanco( "Santander Rio" );
		caja.setNro_cuenta( "5689587/125" );
		caja.setCbu( "1235845896587452" );
		caja.setSucursal( "Av. Rivadavia 8767" );
		caja.setTotal( 15000 );
		caja.setFondo_reserva( 5000 );
		caja.setEstado( "OK" );
		cajaRepository.save( caja );

	}
}

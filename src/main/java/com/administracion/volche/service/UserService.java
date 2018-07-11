package com.administracion.volche.service;

import com.administracion.volche.dao.UserRepository;
import com.administracion.volche.domain.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly=true)
	@Override
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}


	public String CreateUser(String user){
		User newUser = jsonStringToUser(user);
		userRepository.save(newUser);
		return  "Bienvenide " + newUser.getFirstname() + "!";
	}


	private User jsonStringToUser(String json){
		JSONObject serverJson = new JSONObject( json );
		String username = getOrNull( serverJson,"username" );
		String password = getOrNull( serverJson,"password" );
		String role= getOrNull( serverJson,"role" );
		String firstname =  getOrNull( serverJson,"firstname" );
		String lastname=  getOrNull( serverJson,"lastname" );
		User newUser = new User();
		newUser.setUsername( username );
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRole( role );
		newUser.setFirstname( firstname );
		newUser.setLastname( lastname );
		return newUser;
	}

	private static String getOrNull(JSONObject serverJson, String key){
		return serverJson.optString( key,null );
	}



}

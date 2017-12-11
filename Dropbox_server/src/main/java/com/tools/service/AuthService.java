package com.tools.service;


import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tools.entity.users;
import com.tools.repository.ProfileRepository;
import com.tools.repository.UserRepository;
import com.tools.utils.PasswordBcrypt;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	MongoTemplate mongoTemplate ;
	
	@Autowired
	ProfileRepository profileRepository ;

	public boolean registration(users params) {
		if(userRepository.findByEmail(params.getEmail()).size() > 0 ) {
			return false; 
		}else {
			
			try {
				PasswordBcrypt bcrypt = new PasswordBcrypt();
				
				bcrypt.getSecurePassword(params.getPassword(), bcrypt.getSalt());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return userRepository.save(params)!= null ;
		}
	}

	public List<users> login(users params) {
		
		try {
			PasswordBcrypt bcrypt = new PasswordBcrypt();
			bcrypt.getSecurePassword(params.getPassword(), bcrypt.getSalt());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userRepository.findByEmailAndPassword(params.getEmail(), params.getPassword()); 
	} 

	public List<users> getAlreadyLoggedInUser(String id ){
		return userRepository.findById(id);
	}
	
	
	
	
}

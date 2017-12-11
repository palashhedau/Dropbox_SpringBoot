package com.tools.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tools.entity.users;
import com.tools.repository.ProfileRepository;
import com.tools.repository.UserRepository;
import com.tools.utils.BcryptGenerator;

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
			BcryptGenerator bcrypt = new BcryptGenerator();
			
			
			
			
			return userRepository.save(params)!= null ;
		}
	}

	public List<users> login(users params) {
		return userRepository.findByEmailAndPassword(params.getEmail(), params.getPassword()); 
	} 

	public List<users> getAlreadyLoggedInUser(String id ){
		return userRepository.findById(id);
	}
	
	
	
	
}

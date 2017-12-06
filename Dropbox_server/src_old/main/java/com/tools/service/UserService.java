package com.tools.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tools.entity.UserEmails;
import com.tools.repository.UserRepository;
import com.tools.requestparams.UserParams;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	MongoTemplate mongoTemplate ;

	public List<UserEmails> getAllUsers(UserParams params) {
		return  userRepository.findAllOtherEmails(params.getEmail()) ;
	} 
	
	
	
	
}

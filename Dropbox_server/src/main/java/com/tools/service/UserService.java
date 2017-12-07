package com.tools.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.tools.entity.UserEmails;
import com.tools.entity.profile;
import com.tools.repository.ProfileRepository;
import com.tools.repository.UserRepository;
import com.tools.requestparams.UserParams;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	MongoTemplate mongoTemplate ;
	
	@Autowired
	ProfileRepository profileRepository ; 

	public List<UserEmails> getAllUsers(UserParams params) {
		return  userRepository.findAllOtherEmails(params.getEmail()) ;
	}

	public boolean submitProfile(profile params) {
		if(profileRepository.findByEmail(params.getEmail()).size() > 0){
			return false ;
		}else {
			profile prof = profileRepository.save(params);
			return prof != null ; 
		}
	}

	public profile getProfile(profile params) {
		List<profile> profileList = profileRepository.findByEmail(params.getEmail());
		
		if(profileList.size() > 0) {
			Optional<profile> pro = profileList.stream().findFirst() ;
			if(pro.isPresent()) {
				return pro.get() ; 
			}
		}else {
			return null ; 
		}
		
		return null ; 
	}

	public List<profile> checkProfileExist(profile params) {
		return profileRepository.findByEmail(params.getEmail());
	} 
	
	
	
	
	
	
	
}

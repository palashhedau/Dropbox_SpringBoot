package com.tools.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tools.entity.profile;

public interface ProfileRepository extends MongoRepository<profile , String> {
	
	List<profile> findByEmail(String email ) ; 
	
	
}



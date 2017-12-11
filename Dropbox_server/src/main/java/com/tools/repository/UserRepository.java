package com.tools.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tools.entity.UserEmails;
import com.tools.entity.users;

public interface UserRepository extends MongoRepository<users , String> {
	
	@Query("{\"email\" : {$ne : ?0 }} , {\"email\" : 1 }")
	List<UserEmails> findAllOtherEmails(String email) ; 
	
	List<users> findByEmail(String email ) ; 
	
	List<users> findByEmailAndPassword(String email , String password ) ; 
	
	List<users> findById(String id ) ;
	
}
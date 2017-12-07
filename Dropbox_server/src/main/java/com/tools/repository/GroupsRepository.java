package com.tools.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tools.entity.groups;

public interface GroupsRepository extends MongoRepository<groups , String> {
	
	List <groups> findByGroupownerAndGroupname(String owner , String name ) ;

	@Query(value = "{'members.email' : ?0 }")
	List<groups> getGroupsForUser(String email); 
	
	List<groups> findById(String id ) ; 
	
	@Query(value = "{ id : ?0 ,   'members.email' : ?1 }")
	List<groups> findIfPersonIsInGroup(String id , String email); 
	
	long deleteById(String id ) ; 
	
	
	
	
}



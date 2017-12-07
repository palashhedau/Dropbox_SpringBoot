package com.tools.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tools.entity.user_shared_files;

public interface UserSharedFileRepository extends MongoRepository<user_shared_files , String> {
	
	List <user_shared_files> findByFromuserAndTouserAndFilenameAndDirectoryAndIsdirectory(String email , String toUser, String filename , String directory , int isdirectory ); 
	
	List <user_shared_files> findByTouser(String email);
}
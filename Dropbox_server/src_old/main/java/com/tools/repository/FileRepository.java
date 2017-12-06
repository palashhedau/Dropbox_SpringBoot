package com.tools.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tools.entity.user_files;

public interface FileRepository extends MongoRepository<user_files , String> {
	
	/*@Query("{email : ?0 , directory : ?1 , is_deleted : ?2 }")*/
	List<user_files> findByEmailAndDirectoryAndIsdeleted(String email,String directory , int is_deleted);
	
	List<user_files> findByEmailAndIsdeleted(String email,int is_deleted ,  Pageable pageable);
	
	long deleteByEmailAndDirectoryAndFilename(String email , String directory , String filename);
	
	List<user_files> findByEmailAndIsdeletedAndStarred(String email, int isdeleted , int starred);
	
	List<user_files> findByEmailAndIsdeletedAndDirectoryAndFilename(String email , int isDeleted, String directory , String filename );
	
	
	
}



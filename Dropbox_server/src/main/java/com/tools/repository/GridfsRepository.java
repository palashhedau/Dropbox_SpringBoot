package com.tools.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tools.entity.user_files;

public interface GridfsRepository extends MongoRepository<user_files , String> {
	
	
}



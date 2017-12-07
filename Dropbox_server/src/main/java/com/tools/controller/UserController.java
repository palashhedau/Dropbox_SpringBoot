package com.tools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.entity.UserEmails;
import com.tools.entity.profile;
import com.tools.requestparams.UserParams;
import com.tools.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	UserService userService ; 
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserEmails> getAllUsers(@RequestBody UserParams params){
		return userService.getAllUsers(params) ; 
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/submitProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitProfile(@RequestBody profile params){
		return new  ResponseEntity(userService.submitProfile(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProfile(@RequestBody profile params){
		return new  ResponseEntity(userService.getProfile(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/checkProfileExist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkProfileExist(@RequestBody profile params){
		return new  ResponseEntity(userService.checkProfileExist(params) , HttpStatus.OK) ;
	}
	
	
	
}

package com.tools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.entity.users;
import com.tools.service.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
	AuthService authService ; 
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/registration", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean getAllUsers(@RequestBody users params){
		return authService.registration(params) ; 
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody users params){
		return new  ResponseEntity(authService.login(params)  , HttpStatus.OK) ; 
	}
	
	
}

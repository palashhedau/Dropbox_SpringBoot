package com.tools.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.entity.profile;
import com.tools.requestparams.UserParams;
import com.tools.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	UserService userService ; 
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers(@RequestBody UserParams params , HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(userService.getAllUsers(params) , HttpStatus.OK) ;
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/submitProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitProfile(@RequestBody profile params, HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(userService.submitProfile(params) , HttpStatus.OK) ;
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProfile(@RequestBody profile params, HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(userService.getProfile(params) , HttpStatus.OK) ;
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/checkProfileExist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkProfileExist(@RequestBody profile params, HttpSession session){
		if(session.getAttribute("id") != null) {
			return new  ResponseEntity(userService.checkProfileExist(params) , HttpStatus.OK) ;
		}else {
			return new ResponseEntity( HttpStatus.UNAUTHORIZED) ; 
		}
	}
}

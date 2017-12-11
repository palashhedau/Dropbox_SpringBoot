package com.tools.controller;

import java.util.List;
import java.util.Optional;

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

import com.tools.entity.users;
import com.tools.service.AuthService;

@CrossOrigin(origins = "http://localhost:3000" , maxAge = 3600)
@RestController
public class AuthController {
	
	@Autowired
	AuthService authService ; 
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/registration", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers(@RequestBody users params ){
		System.out.println("Register  paras " + params.getEmail());
		return new ResponseEntity(authService.registration(params), HttpStatus.OK) ; 
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody users params , HttpSession session ){
		List <users> user = authService.login(params) ;
		if(user.size() > 0) {
			Optional<users> userFirst = user.stream().findFirst();
			if(userFirst.isPresent()) {
				users usr = userFirst.get();
				session.setAttribute("id", usr.getId());
			}
		}
		return new  ResponseEntity(user , HttpStatus.OK) ; 
	}
	

	@RequestMapping(method=RequestMethod.POST , value="/logout")
	public ResponseEntity<?> logout( HttpSession session ){
		session.invalidate();
		return new  ResponseEntity( false , HttpStatus.OK) ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/checkIfAlreadyLoggedIn")
	public ResponseEntity<?> checkIfAlreadyLoggedIn( HttpSession session ){
		if(session.getAttribute("id") != null) {
			List<users> user = authService.getAlreadyLoggedInUser((String) session.getAttribute("id"));
			Optional<users> usr = user.stream().findFirst();
			if(usr.isPresent()) {
				users UserAlreadyPresent = usr.get();
				return new  ResponseEntity( UserAlreadyPresent , HttpStatus.OK) ; 
			}
		}
		return new  ResponseEntity( false , HttpStatus.UNAUTHORIZED) ; 
	}
}
	
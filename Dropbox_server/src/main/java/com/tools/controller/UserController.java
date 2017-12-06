package com.tools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.entity.UserEmails;
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

}

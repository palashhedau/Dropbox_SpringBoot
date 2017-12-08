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

import com.tools.entity.groups;
import com.tools.entity.user_files;
import com.tools.requestparams.GroupParams;
import com.tools.service.GroupService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {
	
	@Autowired
	GroupService groupService ; 
	
	
	@RequestMapping(method=RequestMethod.POST , value="/createGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean createGroup(@RequestBody GroupParams params){
		return groupService.createGroup(params) ;  
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllGroups", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<groups> getAllGroups(@RequestBody GroupParams params){
		return groupService.getAllGroups(params) ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getGroupName", produces = MediaType.APPLICATION_JSON_VALUE)
	public groups getGroupName(@RequestBody GroupParams params){
		return groupService.getGroupName(params) ;  
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/addMemberToGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> addMemberToGroup(@RequestBody GroupParams params){
		boolean added = groupService.addMemberToGroup(params) ;
		if(added == true ) {
			return new ResponseEntity(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST , value="/getMembersOfGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> getMembersOfGroup(@RequestBody GroupParams params){
		return new ResponseEntity(groupService.getMembersOfGroup(params) , HttpStatus.OK) ; 
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/deleteMembersOfGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> deleteMembersOfGroup(@RequestBody GroupParams params){
		
		if(groupService.deleteMembersOfGroup(params)) {
			return new ResponseEntity( HttpStatus.OK) ;
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/deleteGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> deleteGroup(@RequestBody GroupParams params){
		
		return new  ResponseEntity(groupService.deleteGroup(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/getAllSharedGroupComponents", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> getAllSharedGroupComponents(@RequestBody GroupParams params){
		
		return new  ResponseEntity(groupService.getAllSharedGroupComponents(params) , HttpStatus.OK) ;
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/readFolderForGroups", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> readFolderForGroups(@RequestBody user_files params){
		return new  ResponseEntity(groupService.readFolderForGroups(params) , HttpStatus.OK) ;
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST , value="/readIndividualFolder", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> readFolderForIndividuals(@RequestBody user_files params){
		return new  ResponseEntity(groupService.readFolderForIndividuals(params) , HttpStatus.OK) ;
	}
	
	
	
	
	
	
	
	
}

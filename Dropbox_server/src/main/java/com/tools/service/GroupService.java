package com.tools.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.tools.entity.GroupFiles;
import com.tools.entity.GroupMembers;
import com.tools.entity.groups;
import com.tools.entity.user_files;
import com.tools.repository.FileRepository;
import com.tools.repository.GroupsRepository;
import com.tools.requestparams.GroupParams;

@Service
public class GroupService {
	
	@Autowired
	private GroupsRepository groupRepository ;
	
	@Autowired
	MongoTemplate mongoTemplate ; 
	
	@Autowired
	FileRepository fileRepository ; 
	
	public boolean createGroup(GroupParams params){
		
		
		if( groupRepository.findByGroupownerAndGroupname(params.getEmail(), params.getGroupname()).size() > 0 ) {
			return false; 
		}else{
			groups grp = new groups();
			grp.setGroupname(params.getGroupname());
			grp.setGroupowner(params.getEmail());
			//adding admin as member 
			GroupMembers members = new GroupMembers();
			members.setEmail(params.getEmail());
			members.setGroupowner(params.getEmail());
			
			List <GroupMembers> listOfGroupMember = new ArrayList<>() ; 
			listOfGroupMember.add(members) ;
			
			List <GroupFiles> listOfFiles = new ArrayList<>();
			grp.setFilelist(listOfFiles);
			
			grp.setMembers(listOfGroupMember);
			groups savedGrp = groupRepository.save(grp) ;
			
			if(savedGrp != null) return true; else return false ; 
		}
	}

	public List<groups> getAllGroups(GroupParams params) {
		 List<groups> grps =  groupRepository.getGroupsForUser(params.getEmail());
		return grps ; 
	}

	public groups getGroupName(GroupParams params) {
		List<groups> groupResults =  groupRepository.findById(params.getId()) ; 
		System.out.println("Size of the group " + groupResults.size());
		if(groupResults.size() == 1) {
			Optional<groups> group = groupResults.stream().findAny() ;
			
			if(group.isPresent()) {
				System.out.println("Group is present ");
				return group.get();
				
			}
		}else {
			System.out.println("Group not found");
		}
		return null;
	}

	
	
	
	
	public boolean addMemberToGroup(GroupParams params) {
		List<groups> grp =  groupRepository.findIfPersonIsInGroup(params.getId(), params.getEmailtoadd()); 
		if(grp.size() == 1) {
			//Already present 
			System.out.println("Member Already present ");
			return  false ;
		}else {
			System.out.println("Adding member  ");
			System.out.println("Getting group " + params.getId());
			List <groups> grps = groupRepository.findById(params.getId());
			if(grps.size() == 1 ) {
				Optional<groups> groupToAdd = grps.stream().findFirst();
				if(groupToAdd.isPresent()) {
					groups group = groupToAdd.get();
					System.out.println("Grup to add " + group.getGroupname());
					GroupMembers memberToAdd = new GroupMembers();
					memberToAdd.setEmail(params.getEmailtoadd());
					memberToAdd.setGroupowner(params.getEmail());
					
					List <GroupMembers> memberList = group.getMembers() ;
					memberList.add(memberToAdd) ; 
					
					group.setMembers(memberList);
					groupRepository.save(group); 
					return true ;
				}
			}
		}
		return false;
	}

	public List<GroupMembers> getMembersOfGroup(GroupParams params) {

		List <groups> grps = groupRepository.findById(params.getId());
		if(grps.size() == 1 ) {
			Optional<groups> group = grps.stream().findFirst();
			if(group.isPresent()) {
				groups grp = group.get();
				return grp.getMembers();
			}
		}else {
			return (new ArrayList<GroupMembers>()) ; 
		}
		return null;
	}

	public boolean deleteMembersOfGroup(GroupParams params) {
		System.out.println("Member to delete " + params.getMembertodelete());
		Update update = new Update().pull("members", 
				       new BasicDBObject("email", params.getMembertodelete()));
		
		 WriteResult result  =  mongoTemplate.updateMulti(new Query(), update, groups.class) ; 
		 System.out.println("Result after pull " + result );
		return true ; 
	}

	public boolean deleteGroup(GroupParams params) {
		long deleteResult = groupRepository.deleteById(params.getId());
		return deleteResult ==1 ? true : false ;
	}

	public List<GroupFiles> getAllSharedGroupComponents(GroupParams params) {
		
		List<groups > grp = groupRepository.findById(params.getId()) ; 
		
		if(grp.size() == 1 ) {
			Optional <groups> grpToFind = grp.stream().findFirst() ;
			if(grpToFind.isPresent()) {
				System.out.println();
				return grpToFind.get().getFilelist(); 
			}
		}else {
			List<GroupFiles> EmptyFileList = new ArrayList<>() ;
			return  EmptyFileList; 
		}
		return new ArrayList<>() ; 
	}

	public List<user_files> readFolderForGroups(user_files params) {
		return fileRepository.findByEmailAndDirectoryAndIsdeleted(params.getEmail(), params.getDirectory(), 0) ; 
	}

	public List<user_files> readFolderForIndividuals(user_files params) {
		return fileRepository.findByEmailAndDirectoryAndIsdeleted(params.getEmail(), params.getDirectory(), 0);
	}

	
	
	
}

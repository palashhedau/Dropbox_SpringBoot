package com.tools.entity;

import java.util.List;

public class groups {
	String id ;
	String groupowner , groupname ;
	List <GroupMembers> members ; 
	List <GroupFiles> filelist ;
	
	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupowner() {
		return groupowner;
	}
	public void setGroupowner(String groupowner) {
		this.groupowner = groupowner;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public List<GroupMembers> getMembers() {
		return members;
	}
	public void setMembers(List<GroupMembers> members) {
		this.members = members;
	}
	public List<GroupFiles> getFilelist() {
		return filelist;
	}
	public void setFilelist(List<GroupFiles> filelist) {
		this.filelist = filelist;
	} 
	
	
}	

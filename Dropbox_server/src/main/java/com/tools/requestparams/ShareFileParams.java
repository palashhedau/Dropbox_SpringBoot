package com.tools.requestparams;

public class ShareFileParams {
	String email ; 
	String touser ;
	String filename ; 
	String directory ;
	int isdirectory ;
	String groupname ; 
	String groupowner ; 
	
	
	
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupowner() {
		return groupowner;
	}
	public void setGroupowner(String groupowner) {
		this.groupowner = groupowner;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getIsdirectory() {
		return isdirectory;
	}
	public void setIsdirectory(int isdirectory) {
		this.isdirectory = isdirectory;
	} 
	
	
	
}

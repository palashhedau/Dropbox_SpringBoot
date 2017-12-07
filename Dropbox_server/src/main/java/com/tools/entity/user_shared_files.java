package com.tools.entity;

public class user_shared_files {
	String id;
	String fromuser ; 
	String touser ;
	String filename ;
	String directory ; 
	int isdirectory ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
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

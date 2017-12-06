package com.tools.entity;

import java.util.Date;


public class user_files {
	
	
	private String id ;
	private String email  ;
	private String filename ; 
	private int starred ;
	private int isdirectory ;
	private String directory ;
	private Date fileadddate  ;
	private int isdeleted ; 
	
	public user_files() {
		
	}

	
	public user_files(String id, String email, String file_name, int starred, int is_directory, String directory,
			Date file_add_date) {
		super();
		this.id = id;
		this.email = email;
		this.filename = file_name;
		this.starred = starred;
		this.isdirectory = is_directory;
		this.directory = directory;
		this.fileadddate = file_add_date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getStarred() {
		return starred;
	}


	public void setStarred(int starred) {
		this.starred = starred;
	}


	public int getIsdirectory() {
		return isdirectory;
	}


	public void setIsdirectory(int isdirectory) {
		this.isdirectory = isdirectory;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public Date getFileadddate() {
		return fileadddate;
	}


	public void setFileadddate(Date fileadddate) {
		this.fileadddate = fileadddate;
	}


	public int getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

	


}

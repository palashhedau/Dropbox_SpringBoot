package com.tools.requestparams;

public class GroupParams {
	String email , groupname ;
	String id ; 
	String emailtoadd ; 
	String membertodelete ; 
	
	
	
	public String getMembertodelete() {
		return membertodelete;
	}

	public void setMembertodelete(String membertodelete) {
		this.membertodelete = membertodelete;
	}

	public String getEmailtoadd() {
		return emailtoadd;
	}

	public void setEmailtoadd(String emailtoadd) {
		this.emailtoadd = emailtoadd;
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

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	} 
	
	
}

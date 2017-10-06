package com.swam.web.model;

public class SearchCriteria {

	String username;
	String email;
	int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SearchCriteria [username=" + username + ", email=" + email + "]";
	}

}

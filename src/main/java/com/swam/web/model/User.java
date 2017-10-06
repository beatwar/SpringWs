package com.swam.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;

public class User {

	public static final int STAGE0 = 200;
	public static final int STAGE_VS_G_WAIT = 201;
	public static final int STAGE_VS_H_WAIT = 202;
	public static final int STAGE_VS_G_Y = 203;

	public static final int R_FAIL = 204;
	public static final int STAGE_START = 205;
//	public static final int STAGE_VS_G_N = 4;
//	public static final int STAGE_VS_G_ = 5;
	
	
	@JsonView(Views.Public.class)
	private String sessionId;
	@JsonView(Views.Public.class)
	private String username;
	private int userstage;

	private Long time;
//	String password;
//	@JsonView(Views.Public.class)
//	String email;
//	@JsonView(Views.Public.class)
//	String phone;
//	String address;

	public int getUserstage() {
		return userstage;
	}


	public void setUserstage(int userstage) {
		this.userstage = userstage;
	}


	public User(String sessionId, String username) {
		super();
		this.username = username;
		this.sessionId = sessionId;
		time = System.currentTimeMillis();
		userstage = STAGE0;
	}

	
	public Long getTime() {
		return System.currentTimeMillis() - time;
	}
	
	public void setTime() {
		time = System.currentTimeMillis();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "User [sessionId=" + sessionId + ", username=" + username + "]";
	}

}

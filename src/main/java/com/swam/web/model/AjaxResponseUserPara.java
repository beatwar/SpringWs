package com.swam.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;

public class AjaxResponseUserPara {

	@JsonView(Views.Public.class)
	int code;
	@JsonView(Views.Public.class)
	String username;
	@JsonView(Views.Public.class)
	String playername;
	
	@JsonView(Views.Public.class)
	int left;
	@JsonView(Views.Public.class)
	boolean fire = false;
	
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public boolean isFire() {
		return fire;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
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
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}

	

}

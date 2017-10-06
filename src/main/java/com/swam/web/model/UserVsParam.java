package com.swam.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;

public class UserVsParam {

	@JsonView(Views.Public.class)
	private String name;
	@JsonView(Views.Public.class)
	private int left;
	@JsonView(Views.Public.class)
	private boolean fire;
	
	public UserVsParam(String name){
		this.name = name;
		this.fire = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public boolean isFire() {
		return fire;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	
	
}

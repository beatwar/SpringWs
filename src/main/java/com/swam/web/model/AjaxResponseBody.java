package com.swam.web.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg = "no message.";
	@JsonView(Views.Public.class)
	int code;
	@JsonView(Views.Public.class)
	Collection<User> result;
	@JsonView(Views.Public.class)
	String username;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Collection<User> getResult() {
		return result;
	}

	public void setResult(Collection<User> collection) {
		this.result = collection;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AjaxResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}

}

package com.swam.web.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserMap {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static Map<String, User> userMap = new HashMap<String, User>();

	public synchronized static Map<String, User> getUserMap() {
		return userMap;
	}

	public synchronized static void putUser(String key, User value) {
		userMap.put(key, value);
	}
	
	public synchronized static void removeUser(String key) {
		userMap.remove(key);
	}
	
	public synchronized static User getUser(String key){
		return userMap.get(key);
	}
	
	public synchronized static User getUserByName(String name){
		for(User user : userMap.values()){
			if(user.getUsername().equals(name)){
				return user;
			}
		}
		return null;
	}
}

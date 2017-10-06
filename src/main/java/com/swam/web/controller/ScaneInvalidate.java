package com.swam.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swam.web.model.User;
import com.swam.web.model.UserMap;
import com.swam.web.model.VsParam;
import com.swam.web.model.VsRoom;

public class ScaneInvalidate implements Runnable {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final Long TIMEOUT = (long) 3000;

	@Override
	public void run() {
		try {
			while (true) {

				Thread.sleep(4000);
				
				List<String> removeList = new ArrayList<String>();

				for (User user : UserMap.getUserMap().values()) {
					if (user.getTime() > TIMEOUT) {
						VsParam vsp = VsRoom.getVspByName(user.getUsername());
						if (vsp != null) {
							User guest = UserMap.getUserByName(vsp.getPlayerguest());
							if (null != guest)
								guest.setUserstage(User.STAGE0);
							User host = UserMap.getUserByName(vsp.getPlayerhost());
							if (null != host)
								host.setUserstage(User.STAGE0);
						}
						removeList.add(user.getSessionId());
//						UserMap.removeUser(user.getSessionId());
						
//						logger.info("remove user: " + user.getUsername());
					}
					
				}
				
				if(removeList.size()>0){
					for(String id : removeList){
						UserMap.removeUser(id);
						logger.info("remove user: " + id);
					}
				}
			}
		} catch (Exception e) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			logger.error(e.getMessage());
			new Thread(this).start();;
		}
	}
}

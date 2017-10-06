package com.swam.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;
import com.swam.web.model.AjaxRequestUserPara;
import com.swam.web.model.AjaxResponseBody;
import com.swam.web.model.AjaxResponseUserPara;
import com.swam.web.model.User;
import com.swam.web.model.UserMap;
import com.swam.web.model.VsParam;
import com.swam.web.model.VsRoom;

@RestController
public class GameController {

	protected final Log logger = LogFactory.getLog(getClass());
	
//	private static final int KDOWN_LEFT = 37;
//	private static final int KDOWN_RIGHT = 39;
	private static final int KDOWN_Z = 90;

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/usercontroll")
	public AjaxResponseBody usercontroll(@RequestBody AjaxRequestUserPara search, HttpSession session) {
		AjaxResponseBody rep = new AjaxResponseBody();
		
		logger.debug("search: " + search.getLeft());
		String username = search.getUsername();
		VsParam vsp = VsRoom.getVspByName(username);
		
		// UserVsParam para = new UserVsParam(username);
		if (username.equals(vsp.getHostname())) {
			vsp.setHostleft(search.getLeft());
			if (KDOWN_Z == search.getCode()) {
				vsp.setHostfire(true);
			}
		} else {
			vsp.setGuestleft((search.getLeft()));
			if (KDOWN_Z == search.getCode()) {
				vsp.setGuestfire(true);
			}
		}
		
		rep.setCode(100);
		return rep;
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/youlose")
	public AjaxResponseBody youlose(@RequestBody AjaxRequestUserPara search, HttpSession session) {

		AjaxResponseBody rep = new AjaxResponseBody();
		
		User user = UserMap.getUser(session.getId());
		String username = search.getUsername();
		VsParam vsp = VsRoom.getVspByName(username);
		
		if( null == user || null == vsp || null == vsp.getHostname()){
			if(null != vsp){
				vsp.setHostname("");
				vsp.setGusetname("");
				VsRoom.deletVspByname(username);
			}
		}else{
			if(null != vsp){
				vsp.setHostname("");
				vsp.setGusetname("");
				VsRoom.deletVspByname(username);
			}
		}
		
		return rep;
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/gamebeat")
	public AjaxResponseUserPara gamebeat(@RequestBody AjaxRequestUserPara search, HttpSession session) {
		AjaxResponseUserPara rep = new AjaxResponseUserPara();
		
		User user = UserMap.getUser(session.getId());
		String username = search.getUsername();
		VsParam vsp = VsRoom.getVspByName(username);
		
		if( null == user || null == vsp || null == vsp.getHostname()){
			if(null != vsp){
				if(username.equals(vsp.getHostname())){
					vsp.setHostname("");
				}else{
					vsp.setGusetname("");
				}
			}
			logger.debug("null point, vsp: " + vsp + " session: " + session.getId());
			VsRoom.deletVspByname(username);
			return null;
		}else{
			//logger.debug("not null, vsp: " + vsp +" session: " + session.getId());
			
		}
		user.setTime();
		if(username.equals(vsp.getHostname())){
			if("".equals(vsp.getGusetname())){
				VsRoom.deletVspByname(username);
				return null;
			}
			vsp.setHostleft(search.getLeft());
			
			rep.setLeft(vsp.getGuestleft());
			rep.setCode(100);
			rep.setFire(vsp.isGuestfire());
			vsp.setGuestfire(false);
		}else{
			if("".equals(vsp.getHostname())){
				VsRoom.deletVspByname(username);
				return null;
			}
			vsp.setGuestleft(search.getLeft());
			
			rep.setLeft(vsp.getHostleft());
			rep.setCode(100);
			rep.setFire(vsp.isHostfire());
			vsp.setHostfire(false);
		}
		
		
		return rep;
	}
}

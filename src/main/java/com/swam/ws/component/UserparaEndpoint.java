package com.swam.ws.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.swam.web.model.User;
import com.swam.web.model.UserMap;
import com.swam.web.model.VsParam;
import com.swam.web.model.VsRoom;
import com.swam.ws.schema.GetUserparaRequest;
import com.swam.ws.schema.GetUserparaResponse;

@Endpoint
public class UserparaEndpoint {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final int KDOWN_Z = 90;

	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserparaRequest")
	@ResponsePayload
	public GetUserparaResponse getUserparaRequest(@RequestPayload GetUserparaRequest request) {
		GetUserparaResponse response = new GetUserparaResponse();
		
		if("login".equals(request.getMethod())){
			response = login(request);
		}else if("chooseplayer".equals(request.getMethod())){
			response = chooseplayer(request);
		}else if("vsConfrm".equals(request.getMethod())){
			response = vsConfrm(request);
		}else if("heartbeat".equals(request.getMethod())){
			response = heartbeat(request);
		}else if("usercontroll".equals(request.getMethod())){
			response = usercontroll(request);
		}else if("youlose".equals(request.getMethod())){
			response = youlose(request);
		}else if("gamebeat".equals(request.getMethod())){
			response = gamebeat(request);
		}
		
		//logger.debug("name: " + request.getUsername());

		return response;
	}

	private GetUserparaResponse gamebeat(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		
		User user = UserMap.getUser(request.getId());
		String username = request.getUsername();
		VsParam vsp = VsRoom.getVspByName(username);
		
		
		if( null == user || null == vsp || null == vsp.getHostname()){
			if(null != vsp){
				if(username.equals(vsp.getHostname())){
					vsp.setHostname("");
				}else{
					vsp.setGusetname("");
				}
			}
			logger.debug("null point, vsp: " + vsp + " session: " + request.getId());
			VsRoom.deletVspByname(username);
			return null;
		}else{
			//logger.debug("not null, vsp: " + vsp +" session: " + request.getId() + " left: " + request.getLeft());
			
		}
		user.setTime();
		if(username.equals(vsp.getHostname())){
			if("".equals(vsp.getGusetname())){
				VsRoom.deletVspByname(username);
				return null;
			}
			
			Double x = Double.parseDouble(request.getLeft());
			x = (x - 42)/685 * 248 +2;
			
			vsp.setHostleft(x.intValue());
			
			result.setLeft(vsp.getGuestleft());
			result.setCode(100);
			result.setFire(vsp.isGuestfire());
			vsp.setGuestfire(false);
		}else{
			if("".equals(vsp.getHostname())){
				VsRoom.deletVspByname(username);
				return null;
			}
			Double x = Double.parseDouble(request.getLeft());
			x = (x - 2)/248 * 685 + 42;
			vsp.setGuestleft(x.intValue());
			
			result.setLeft(vsp.getHostleft());
			result.setCode(100);
			result.setFire(vsp.isHostfire());
			vsp.setHostfire(false);
		}
		
		return result;
	}

	private GetUserparaResponse youlose(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		
		User user = UserMap.getUser(request.getId());
		String username = request.getUsername();
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
		
		
		return result;
	}

	private GetUserparaResponse usercontroll(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		
		logger.debug("search: " + request.getLeft());
		String username = request.getUsername();
		VsParam vsp = VsRoom.getVspByName(username);
		
		// UserVsParam para = new UserVsParam(username);
		if (username.equals(vsp.getHostname())) {
		
			Double x = Double.parseDouble(request.getLeft());
			x = (x - 42)/685 * 248 +2;
			
			vsp.setHostleft(x.intValue());
			if (KDOWN_Z == Integer.parseInt(request.getCode())) {
				vsp.setHostfire(true);
			}
		} else {
			vsp.setGuestleft(Integer.parseInt(request.getLeft()));
			if (KDOWN_Z == Integer.parseInt(request.getCode())) {
				vsp.setGuestfire(true);
			}
		}
		
		result.setCode(100);
		
		return result;
	}

	private GetUserparaResponse heartbeat(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		
		User user = UserMap.getUser(request.getId());
		user.setTime();
		
		switch(user.getUserstage()){
		case User.STAGE0:
			result.setCode(User.STAGE0);
			List<String> userlist = new ArrayList<String>();
			for(User usr : UserMap.getUserMap().values()){
				userlist.add(usr.getUsername());
			}
			result.setUserlist(userlist);
			break;
		case User.STAGE_VS_G_WAIT:
			result.setCode(User.STAGE_VS_G_WAIT);
			
			VsParam vsp = VsRoom.getVspByName(user.getUsername());
			result.setUsername(vsp.getPlayerhost());
			
			break;
		case User.STAGE_VS_H_WAIT:
			result.setCode(User.STAGE_VS_H_WAIT);
			break;
//		case User.STAGE_VS_G_N:
//			result.setCode("203");
//			break;
		case User.STAGE_VS_G_Y:
			result.setCode(User.STAGE_VS_G_Y);
			break;
		case User.STAGE_START:
			if(!VsRoom.getVspByName(user.getUsername()).getPlayerhost().equals(user.getUsername())){
				User userhost = findByUser(VsRoom.getVspByName(user.getUsername()).getPlayerhost(), "");
				
				userhost.setUserstage(User.STAGE_START);
			}
			result.setCode(User.STAGE_START);
			break;
		}
		
		return result;
	}

	private GetUserparaResponse vsConfrm(GetUserparaRequest request) {
		
		GetUserparaResponse result = new GetUserparaResponse();
		
		User userguest = findByUser("", request.getId());
		User userhost = findByUser(request.getUsername(), "");
		
		if(1==Integer.parseInt(request.getCode())){
			logger.debug(userguest.getUsername()+ "vs" + userhost.getUsername() + " start.");
			userguest.setUserstage(User.STAGE_START);
			userhost.setUserstage(User.STAGE_VS_G_Y);
		}else{
			userguest.setUserstage(User.STAGE0);
			userhost.setUserstage(User.STAGE0);
			VsRoom.deletVspByname(userhost.getUsername());
		}
		
		
		return result;
	}

	private GetUserparaResponse chooseplayer(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		
		User userhost = findByUser("", request.getId());
		User userguest = findByUser(request.getUsername(), "");
		VsParam vsp = new VsParam(userhost, userguest);
		userhost.setUserstage(User.STAGE_VS_H_WAIT);
		userguest.setUserstage(User.STAGE_VS_G_WAIT);
		VsRoom.addVsp(vsp);
		
		return result;
	}

	private GetUserparaResponse login(GetUserparaRequest request) {
		GetUserparaResponse result = new GetUserparaResponse();
		User user = findByUser(request.getUsername(), request.getId());
		logger.debug(request.getUsername() + " : " +request.getId());
		if (user != null) {
			result.setCode(User.R_FAIL);
			//result.setMsg("already register! please try another name.");
		} else {
			result.setCode(User.STAGE0);
			user = new User(request.getId(), request.getUsername());
			//result.setResult(UserMap.getUserMap().values());
			
			List<String> userlist = new ArrayList<String>();
			for(User usr : UserMap.getUserMap().values()){
				userlist.add(usr.getUsername());
			}
			result.setUserlist(userlist);
			UserMap.putUser(request.getId(), user);
//			result.setMsg("register success.");
		}
		return result;
	}
	
	private User findByUser(String username, String id) {

		User result = null;

		for (User user : UserMap.getUserMap().values()) {

			if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(id)) {

				if (username.equals(user.getUsername()) && !user.getSessionId().equals(id)) {
					result = user;
					break;
				} else {
					continue;
				}
			}
			
			if ((StringUtils.isEmpty(username)) && !StringUtils.isEmpty(id)) {

				if ( id.equals(user.getSessionId())) {
					result = user;
					break;
				} else {
					continue;
				}
			}
			
			if ((!StringUtils.isEmpty(username)) && StringUtils.isEmpty(id)) {

				if ( username.equals(user.getUsername())) {
					result = user;
					break;
				} else {
					continue;
				}
			}
		}

		return result;
	}
}

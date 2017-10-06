package com.swam.web.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.swam.web.jsonview.Views;
import com.swam.web.model.AjaxResponseBody;
import com.swam.web.model.SearchCriteria;
import com.swam.web.model.User;
import com.swam.web.model.UserMap;
import com.swam.web.model.VsParam;
import com.swam.web.model.VsRoom;

@RestController
public class AjaxController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/destroySession")
	public void destroySession(HttpSession session){
		session.invalidate();
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/api/choosePlayer")
	public AjaxResponseBody choosePlayer(@RequestBody SearchCriteria search, HttpSession session) {
		AjaxResponseBody result = new AjaxResponseBody();
		User userhost = findByUser("", session.getId());
		User userguest = findByUser(search.getUsername(), "");
		VsParam vsp = new VsParam(userhost, userguest);
		userhost.setUserstage(User.STAGE_VS_H_WAIT);
		userguest.setUserstage(User.STAGE_VS_G_WAIT);
		VsRoom.addVsp(vsp);

		return result;
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/vsConfrm")
	public AjaxResponseBody vsConfrm(@RequestBody SearchCriteria search, HttpSession session) {
		AjaxResponseBody result = new AjaxResponseBody();
		User userguest = findByUser("", session.getId());
		User userhost = findByUser(search.getUsername(), "");
		
		if(1==search.getCode()){
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
	

	@JsonView(Views.Public.class)
	@RequestMapping(value = "/heartbeat")
	public AjaxResponseBody heartbeat(HttpSession session){
		
		AjaxResponseBody result = new AjaxResponseBody();
		
		User user = UserMap.getUser(session.getId());
		user.setTime();
		
		switch(user.getUserstage()){
		case User.STAGE0:
			result.setCode(User.STAGE0);
			result.setResult(UserMap.getUserMap().values());
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
		
//		logger.debug(user.getUsername() + " heartbeat.");
		
		
		return result;
	}
	
	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/api/getSearchResult")
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search, HttpSession session) {

		AjaxResponseBody result = new AjaxResponseBody();

		User user = findByUser(search.getUsername(), session.getId());

		if (user != null) {
			result.setCode(User.R_FAIL);
			result.setMsg("already register! please try another name.");
		} else {
			result.setCode(User.STAGE0);
			user = new User(session.getId(), search.getUsername());
			result.setResult(UserMap.getUserMap().values());
			UserMap.putUser(session.getId(), user);

			result.setMsg("register success.");
		}

		logger.info(UserMap.getUserMap().values());

		// AjaxResponseBody will be converted into json format and send back to
		// client.
		return result;

	}

//	private boolean isValidSearchCriteria(SearchCriteria search) {
//
//		boolean valid = true;
//
//		if (search == null) {
//			valid = false;
//		}
//
//		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
//			valid = false;
//		}
//
//		return valid;
//	}

	// Init some users for testing
	@PostConstruct
	private void iniDataForTesting() {

		new Thread(new ScaneInvalidate()).start();
		
//		User user1 = new User("swam", "pass123", "swam@yahoo.com", "012-1234567", "address 123");
//		User user2 = new User("yflow", "pass456", "yflow@yahoo.com", "016-7654321", "address 456");
//		User user3 = new User("laplap", "pass789", "swam@yahoo.com", "012-111111", "address 789");
//		users.add(user1);
//		users.add(user2);
//		users.add(user3);

	}

	// Simulate the search function
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

package com.swam.ws.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.swam.ws.schema.CodeResponse;
import com.swam.ws.schema.Userws;

@Endpoint
public class UserEndpoint {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String NAMESPACE_URI = "http://spring.io/guides/start";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "login")
	@ResponsePayload
	public CodeResponse login(@RequestPayload Userws request) {
		CodeResponse response = new CodeResponse();
		

		//System.out.println(countriesMap.select("09002001000010"));
		//logger.info("username: " + request.getUsername());
		
		response.setCode("200");
		return response;
	}
}

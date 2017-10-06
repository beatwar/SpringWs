package com.swam.ws.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.swam.mappers.CountriesMap;
import com.swam.ws.schema.GetCountryRequest;
import com.swam.ws.schema.GetCountryResponse;
import com.swam.ws.schema.GetUserparaRequest;
import com.swam.ws.schema.GetUserparaResponse;

@Endpoint
public class CountryEndpoint {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private CountryRepository countryRepository;

	@Autowired
	private CountriesMap countriesMap;

	public void setCountriesMap(CountriesMap countriesMap) {
		this.countriesMap = countriesMap;
	}

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		logger.debug("getcountry.");
		//System.out.println(countriesMap.select("09002001000010"));

		return response;
	}
	
}

package com.swam.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swam.mappers.CountriesMap;

@Controller
public class WelcomeController {

	@Autowired
	private CountriesMap countriesMap;

	@RequestMapping(value = "/welcome")
	public String printWelcome( ModelMap model) {


		return "welcome";
	}
	
	@RequestMapping(value = "/scen0")
	public String scen0(
			@RequestParam("username") String username,
			@RequestParam("playername") String playername,
			ModelMap model) {
		model.addAttribute("username", username);
		model.addAttribute("playername", playername);

		return "scen0";
	}

}

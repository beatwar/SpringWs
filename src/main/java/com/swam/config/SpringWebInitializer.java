package com.swam.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer  {

	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/s/*" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	  @Override
	  protected String getServletName() {
	    return "SpringServlet";
	  }


}
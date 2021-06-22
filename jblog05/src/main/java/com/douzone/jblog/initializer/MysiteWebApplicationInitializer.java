package com.douzone.jblog.initializer;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.jblog.config.AppConfig;
import com.douzone.jblog.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context's config class
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context's Configuration Class
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Dispatcher Servlet Mapping URL
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// Encoding Filter
		return new Filter[] { new CharacterEncodingFilter("UTF-8", false) };
	}

}

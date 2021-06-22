package com.douzone.jblog.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.jblog.security.AuthIntercepter;
import com.douzone.jblog.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.jblog.security.LoginIntercepter;
import com.douzone.jblog.security.LogoutIntercepter;

@SpringBootConfiguration
@PropertySource("classpath:com/douzone/jblog/config/Webconfig.properties")
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;

	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(handlerMethodArgumentResolver());
	}

	// Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginIntercepter();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutIntercepter();
	}

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthIntercepter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns(env.getProperty("security.auth-url"));
		registry.addInterceptor(logoutInterceptor()).addPathPatterns(env.getProperty("security.logout"));
		registry.addInterceptor(authInterceptor()).addPathPatterns("/**").addPathPatterns(env.getProperty("security.auth-url"))
				.addPathPatterns(env.getProperty("security.logout")).addPathPatterns("/assets/**");
	}

	// Message Converters
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));

		return messageConverter;
	}

	// MappingJackson2HttpMessageConverter
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//			Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//			builder.indentOutput(true);
//			builder.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));	//안해줘도됨

		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-mm-dd")); // 안해줘도됨

		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));
		return messageConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}

	// Resource Mapping(URL Magic Mapping)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
				.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	}

}

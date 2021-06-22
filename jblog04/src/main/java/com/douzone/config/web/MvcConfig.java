package com.douzone.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{

	// View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewresolver = new InternalResourceViewResolver();
		viewresolver.setViewClass(JstlView.class);
		viewresolver.setPrefix("/WEB-INF/views/");
		viewresolver.setSuffix(".jsp");
		viewresolver.setExposeContextBeansAsAttributes(true);
		return viewresolver;
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
//		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//		builder.indentOutput(true);
//		builder.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));	//안해줘도됨
		
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));	//안해줘도됨
		
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("utf-8"))));
		return messageConverter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}

	
	// Default Servlet handler 등록 작업
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}

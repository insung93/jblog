package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@ResponseBody
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(
			@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}

		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		return "BlogController.index";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		System.out.println("id: "+id);
		return "BlogController.adminBasic";
	}
}

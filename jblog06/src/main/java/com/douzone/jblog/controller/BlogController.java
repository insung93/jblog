package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private ServletContext application;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			PostVo postVo = postService.firstPost(categoryNo);
			if(postVo == null) {
				postNo = 0L;
			} else {
				postNo = (long) postVo.getNo();
			}
		} else {
			CategoryVo categoryVo = categoryService.firstCategory(id);
			if(categoryVo == null) {
				categoryNo = 0L;
			} else {
				categoryNo = (long) categoryVo.getNo();
			}
			PostVo postVo = postService.firstPost(categoryNo);
			if(postVo == null) {
				postNo = 0L;
			} else {
				postNo = (long) postVo.getNo();
			}
		}
		
		BlogVo blogVo = blogService.findBlog(id);
		List<CategoryVo> categoryList = categoryService.findCategory(id);
		List<PostVo> postList = postService.findPostList(categoryNo);
		PostVo postVo = postService.findPostOne(postNo);
		
		model.addAttribute("categoryNo",categoryNo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo",postVo);
		application.setAttribute("blogvo", blogVo);
		return "blog/main";
	}
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		return "blog/admin/basic";
	}
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = categoryService.findCount(id);
		model.addAttribute("categoryList",categoryList);
		return "blog/admin/category";
	}
	@Auth
	@RequestMapping("/admin/addcategory")
	public String adminAddCategory(@PathVariable("id") String id, CategoryVo vo) {
		vo.setBlogId(id);
		categoryService.addCategory(vo);
		return "redirect:/" + id + "/admin/category";
	}
	@Auth
	@RequestMapping(value = "/admin/write" , method = RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = categoryService.findCategory(id);
		model.addAttribute("categoryList",categoryList);
		return "blog/admin/write";
	}
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo, Model model) {
		postService.addPost(postVo);
		return "redirect:/" + id;
	}
	@Auth
	@RequestMapping(value = "/admin/deletecategory", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, @RequestParam("no") int no ) {
		postService.deletePost(no);
		categoryService.deleteCategory(no);
		return "redirect:/" + id + "/admin/category";
	}
	@Auth
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String deleteCategory(@PathVariable("id") String id, 
								@ModelAttribute BlogVo vo, @RequestParam("file") MultipartFile file, 
								Model model) {
		if(file.isEmpty()) {
			blogService.update(vo);
			application.setAttribute("blogvo", vo);
			return "redirect:/" + id + "/admin/basic";
		}
		String url = fileUploadService.restore(file);
		vo.setLogo(url);
		blogService.update(vo);
		application.setAttribute("blogvo", vo);
		return "redirect:/" + id + "/admin/basic";
	}
	
	
}

package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;

@RestController("categoryControllerApi")
@RequestMapping("/{id:(?!assets).*}")
public class CategoryControllerApi {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@Auth
	@PostMapping("/admin/addcategory")
	public JsonResult addCategory(
			@PathVariable("id") String id,
			@RequestBody CategoryVo vo) {
		vo.setBlogId(id);
		categoryService.addCategory(vo);
		vo.setCount(0L);
		return JsonResult.success(vo);
	}
	
	@Auth
	@GetMapping("/admin/list")
	public JsonResult categoryList(@PathVariable("id") String id) {
		List<CategoryVo> categoryList = categoryService.findCount(id);
		return JsonResult.success(categoryList);
	}
	
	@Auth
	@DeleteMapping("/admin/delete/{no}")
	public JsonResult deleteCategory(@PathVariable("id") String id, @PathVariable("no") int no ) {
		System.out.println(no);
		postService.deletePost(no);
		categoryService.deleteCategory(no);
		return JsonResult.success(no);
	}
	
}

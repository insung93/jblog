package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public void insert(String id) {
		categoryRepository.insert(id);
	}

	public List<CategoryVo> findCategory(String id) {
		return categoryRepository.findCategory(id);
	}

	public CategoryVo firstCategory(String id) {
		return categoryRepository.firstCategory(id);
	}

	public void addCategory(CategoryVo vo) {
		categoryRepository.addCategory(vo);	
	}

	public List<CategoryVo> findCount(String id) {
		return categoryRepository.findCount(id);
	}

	public void deleteCategory(int no) {
		categoryRepository.deleteCategory(no);	
		
	}
	
}

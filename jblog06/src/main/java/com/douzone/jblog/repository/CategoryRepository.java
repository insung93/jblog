package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id) {
		sqlSession.insert("category.insert", id);
	}

	public List<CategoryVo> findCategory(String id) {
		return sqlSession.selectList("category.findCategory",id);
	}

	public CategoryVo firstCategory(String id) {
		return sqlSession.selectOne("category.firstCategory",id);
	}

	public void addCategory(CategoryVo vo) {
		sqlSession.insert("category.addCategory",vo);		
	}

	public List<CategoryVo> findCount(String id) {
		return sqlSession.selectList("category.findCount",id);
	}

	public void deleteCategory(int no) {
		sqlSession.delete("category.deleteCategory",no);	
	}
}

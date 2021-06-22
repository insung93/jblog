package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> findPostList(Long categoryNo) {
		return sqlSession.selectList("post.findPostList", categoryNo);
	}

	public PostVo firstPost(Long categoryNo) {
		return sqlSession.selectOne("post.firstPost", categoryNo);
	}

	public PostVo findPostOne(Long postNo) {
		return sqlSession.selectOne("post.findPostOne", postNo.intValue());
	}

	public void addPost(PostVo postVo) {
		sqlSession.insert("post.addPost", postVo);
	}

	public void deletePost(int no) {
		sqlSession.delete("post.deletePost",no);
	}
}

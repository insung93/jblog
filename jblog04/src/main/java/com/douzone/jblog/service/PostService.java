package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public List<PostVo> findPostList(Long categoryNo) {
		return postRepository.findPostList(categoryNo);
	}

	public PostVo firstPost(Long categoryNo) {
		return postRepository.firstPost(categoryNo);
	}

	public PostVo findPostOne(Long postNo) {
		return postRepository.findPostOne(postNo);
	}

	public void addPost(PostVo postVo) {
		postRepository.addPost(postVo);
	}

	public void deletePost(int no) {
		postRepository.deletePost(no);
	} 
}

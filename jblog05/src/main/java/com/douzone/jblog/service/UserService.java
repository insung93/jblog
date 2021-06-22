package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void insert(UserVo vo) {
		userRepository.insert(vo);
	}

	public UserVo findByIdandPassword(UserVo vo) {
		return userRepository.findByIdandPassword(vo);
	}

	public UserVo getUser(String id) {
		return userRepository.getUser(id);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.getUser(id, password);
	}

}

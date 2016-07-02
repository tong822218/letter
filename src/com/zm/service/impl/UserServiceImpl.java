package com.zm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zm.common.Pager;
import com.zm.dao.UserDao;
import com.zm.model.User;
import com.zm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getByNameAndPassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(String id) {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}
	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getByName(name);
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pager<?> findPager(Pager<?> pager) {
		// TODO Auto-generated method stub
		return null;
	}

}

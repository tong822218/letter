package com.zm.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zm.common.Pager;
import com.zm.model.User;

@Transactional(rollbackFor = { Exception.class })
public interface UserService {

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public User getByNameAndPassword(User user);

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<User> getAll();

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public User getById(String id);

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void add(User user);

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void update(User user);

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean delete(String id);

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Pager<?> findPager(Pager<?> pager);

}

package com.zm.dao;

import com.zm.common.Pager;
import com.zm.model.User;

public interface UserDao {
	public Pager<?> findPager(Pager<?> pager);

	public User getById(String id);

	public void add(User letter);

	public void update(User letter);

	public User getByName(String name);

	public User getByToken(String token);
}

package com.zm.dao.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zm.common.Pager;
import com.zm.dao.JdbcDao;
import com.zm.dao.UserDao;
import com.zm.model.Letter;
import com.zm.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	private JdbcDao mysqlJdbcDao;

	public JdbcDao getMysqlJdbcDao() {
		return mysqlJdbcDao;
	}
	@Resource
	public void setMysqlJdbcDao(JdbcDao mysqlJdbcDao) {
		this.mysqlJdbcDao = mysqlJdbcDao;
	}

	@Override
	public Pager<?> findPager(Pager<?> pager) {
		// TODO Auto-generated method stub
		return mysqlJdbcDao.findPager(pager, "select * from u_seller");
	}
	
	@Override
	public User getById(String id) {
		ArrayList list = new ArrayList();
		String sql = "select * from u_seller where id = ?";
		list.add(id);
		return (User)mysqlJdbcDao.queryForObject(sql, list.toArray(), User.class);
	}
	@Override
	public User getByName(String name) {
		ArrayList list = new ArrayList();
		String sql = "select * from u_seller where name = ?";
		list.add(name);
		return (User)mysqlJdbcDao.queryForObject(sql, list.toArray(), User.class);
	}
	@Override
	public void add(User letter) {
		String sql = "insert into u_seller(id,username,password,name,token,create_time,seller_say,sel_cards) values ('"+letter.getId()+"','"+letter.getUsername()+"','"+letter.getPassword()+"','"+letter.getName()+"','"+letter.getToken()+"','"+letter.getCreate_time()+"','"+letter.getSeller_say()+"','"+letter.getSel_cards()+"') ";
		mysqlJdbcDao.execSql(sql);
	}
	@Override
	public void update(User letter) {
		String sql = "update  u_seller set id='"+letter.getId()+"',username='"+ letter.getUsername()+"',password='"+ letter.getPassword()+"',name='"+ letter.getName()+"',token='"+ letter.getToken()+"',create_time='"+ letter.getCreate_time()+"',seller_say='"+ letter.getSeller_say()+"',sel_cards='"+ letter.getSel_cards()+"' where id='"+letter.getId()+"'";
		mysqlJdbcDao.execSql(sql);
	}
	@Override
	public User getByToken(String token) {
		ArrayList list = new ArrayList();
		String sql = "select * from u_seller where token = ?";
		list.add(token);
		return (User)mysqlJdbcDao.queryForObject(sql, list.toArray(), User.class);
	}
}

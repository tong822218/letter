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
		return mysqlJdbcDao.findPager(pager, "select * from t_user");
	}
	
	@Override
	public User getById(String id) {
		ArrayList list = new ArrayList();
		String sql = "select * from u_user where id = ?";
		list.add(id);
		return (User)mysqlJdbcDao.queryForObject(sql, list.toArray(), User.class);
	}
	@Override
	public void add(User letter) {
		String sql = "insert into u_user(id,username,password,name,token,create_time,seller_say) values ('"+letter.getId()+"','"+letter.getUsername()+"','"+letter.getPassword()+"','"+letter.getName()+"','"+letter.getToken()+"','"+letter.getCreate_time()+"','"+letter.getSeller_say()+"') ";
		mysqlJdbcDao.execSql(sql);
	}
	@Override
	public void update(User letter) {
		String sql = "update  u_user set id='"+letter.getId()+"',username='"+ letter.getUsername()+"',password='"+ letter.getPassword()+"',name='"+ letter.getName()+"',token='"+ letter.getToken()+"',create_time='"+ letter.getCreate_time()+"',seller_say='"+ letter.getSeller_say()+"' where id='"+letter.getId()+"'";
		mysqlJdbcDao.execSql(sql);
	}
}

package com.zm.dao.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zm.dao.JdbcDao;
import com.zm.dao.LetterDao;
import com.zm.model.Letter;

@Repository("letterDao")
public class LetterDaoImpl implements LetterDao {
	private JdbcDao mysqlJdbcDao;
	@Override
	public void add(Letter letter) {
		String sql = "insert into s_letter(id,user,userid,temp,imgs,params,create_time) values ('"+letter.getId()+"','"+letter.getUser()+"','"+letter.getUserid()+"','"+letter.getTemp()+"','"+letter.getImgs()+"','"+letter.getParams()+"','"+letter.getCreateTime()+"') ";
		mysqlJdbcDao.execSql(sql);
	}

	@Override
	public Letter getById(String id) {
		ArrayList list = new ArrayList();
		String sql = "select * from s_letter where id = ?";
		list.add(id);
		return (Letter)mysqlJdbcDao.queryForObject(sql, list.toArray(), Letter.class);
	}

	public JdbcDao getMysqlJdbcDao() {
		return mysqlJdbcDao;
	}
	@Resource
	public void setMysqlJdbcDao(JdbcDao mysqlJdbcDao) {
		this.mysqlJdbcDao = mysqlJdbcDao;
	}
	
}

package com.zm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zm.dao.JdbcDao;
import com.zm.dao.TempDao;
import com.zm.model.Temp;

@Repository("tempDao")
public class TempDaoImpl implements TempDao{
	private JdbcDao mysqlJdbcDao;

	public JdbcDao getMysqlJdbcDao() {
		return mysqlJdbcDao;
	}
	@Resource
	public void setMysqlJdbcDao(JdbcDao mysqlJdbcDao) {
		this.mysqlJdbcDao = mysqlJdbcDao;
	}
	@Override
	public Temp getById(String id) {
		ArrayList list = new ArrayList();
		String sql = "select * from s_temp where id = ?";
		list.add(id);
		return (Temp)mysqlJdbcDao.queryForObject(sql, list.toArray(), Temp.class);
	}
	@Override
	public List<Temp> getList() {
		ArrayList list = new ArrayList();
		String sql = "select * from s_temp";
		return (List)mysqlJdbcDao.queryList(sql,Temp.class);
	}
	
}

package com.zm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zm.dao.JdbcDao;
import com.zm.dao.MsgDao;
import com.zm.model.Msg;

@Repository("msgDao")
public class MsgDaoImpl implements MsgDao {
	private JdbcDao mysqlJdbcDao;
	@Override
	public Msg getById(String id) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select * from s_msg where id = ?";
		list.add(id);
		return (Msg)mysqlJdbcDao.queryForObject(sql, list.toArray(), Msg.class);
	}
	@Override
	public List<Msg> getByTel(String tel) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = " select * from s_msg where tel = ? order by create_time desc ";
		list.add(tel);
		return (List<Msg>) mysqlJdbcDao.queryList(sql, list.toArray(), Msg.class);
	}
	@Override
	public void add(Msg msg) {
		String sql = "insert into s_msg(id,sender,tel,content,create_time) values ('"+msg.getId()+"','"+msg.getSender()+"','"+msg.getTel()+"','"+msg.getContent()+"','"+msg.getCreateTime()+"') ";
		mysqlJdbcDao.execSql(sql);
	}
	
	public JdbcDao getMysqlJdbcDao() {
		return mysqlJdbcDao;
	}
	@Resource
	public void setMysqlJdbcDao(JdbcDao mysqlJdbcDao) {
		this.mysqlJdbcDao = mysqlJdbcDao;
	}


}

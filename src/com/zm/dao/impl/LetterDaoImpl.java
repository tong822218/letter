package com.zm.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
		String sql = "insert into s_letter(id,user,userid,temp,imgs,params,create_time,seller,tel,sender,sender_tel) values ('"+letter.getId()+"','"+letter.getUser()+"','"+letter.getUserid()+"','"+letter.getTemp()+"','"+letter.getImgs()+"','"+letter.getParams()+"','"+letter.getCreateTime()+"','"+letter.getSeller()+"','"+letter.getTel()+"','"+letter.getSender()+"','"+letter.getSenderTel()+"') ";
		mysqlJdbcDao.execSql(sql);
	}

	@Override
	public Letter getById(String id) {
		ArrayList list = new ArrayList();
		String sql = "select * from s_letter where id = ?";
		list.add(id);
		return (Letter)mysqlJdbcDao.queryForObject(sql, list.toArray(), Letter.class);
	}
	@Override
	public List<Letter> getByTel(String tel) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = " select * from s_letter where tel = ? order by create_time desc ";
		list.add(tel);
		return (List<Letter>) mysqlJdbcDao.queryList(sql, list.toArray(), Letter.class);
	}

	public JdbcDao getMysqlJdbcDao() {
		return mysqlJdbcDao;
	}
	@Resource
	public void setMysqlJdbcDao(JdbcDao mysqlJdbcDao) {
		this.mysqlJdbcDao = mysqlJdbcDao;
	}

	@Override
	public List<Letter> getBySeller(String sellerName) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = " select *,l.params params,t.title  tempName from s_letter l left join s_temp t on l.temp=t.id  where seller = ? order by l.create_time desc ";
		list.add(sellerName);
		return (List<Letter>) mysqlJdbcDao.queryList(sql, list.toArray(), Letter.class);
	}

	@Override
	public List<Letter> getChart(Letter letter) {
		ArrayList<String> list = new ArrayList<String>();
		String sql = " select DATE_FORMAT(create_time, '%Y-%m') as month,COUNT(1) as useTimes,SUM(open_times) as openTimes FROM s_letter where seller = ? and create_time > ? group by month(create_time) ";
		list.add(letter.getSeller());
		list.add(letter.getCreateTime());
		return (List<Letter>) mysqlJdbcDao.queryList(sql, list.toArray(), Letter.class);
	}

	
}

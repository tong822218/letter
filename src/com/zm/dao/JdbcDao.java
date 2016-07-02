package com.zm.dao;

import java.util.List;
import java.util.Map;

import com.zm.common.Pager;


public interface JdbcDao {


	public void execSql(String sql);

	public void update(String sql);

	public void update(String sql, Object[] params);

	public Object queryForObject(String sql, Object[] params, Class<?> clazz);

	public List<?> queryList(String sql);

	public List<?> queryList(String sql, Class<?> clazz);

	public List<?> queryList(String sql, Object[] params);
	
	public List<?> queryList(String sql, Object[] params, Class<?> clazz);

	public Map<String, Object> queryForMap(String sql, Object[] params);

	public Pager<?> findPager(Pager<?> pager, String sql, Object[] sqlObject);

	public Pager<?> findPager(Pager<?> pager, String sql);

	public Pager<?> findPagerSuppertTag(Pager<?> pager, String sql);

}

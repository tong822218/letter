package com.zm.dao.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zm.common.Pager;
import com.zm.dao.JdbcDao;

public class JdbcDaoImpl implements JdbcDao{


	protected JdbcTemplate jdbcTemplate;

	public void execSql(String sql) {
		// eg:("CREATE TABLE USER (user_id integer, name varchar(100))");
		jdbcTemplate.execute(sql);
	}

	public void update(String sql) {
		// eg:("INSERT INTO USER VALUES('1','2',2)");
		jdbcTemplate.update(sql);
	}

	public void update(String sql, Object[] params) {
		// eg:("UPDATE USER SET name = ? WHERE user_id = ?", new Object[] {name,
		// id});
		jdbcTemplate.update(sql, params);
	}

	public Object queryForObject(String sql, Object[] params, Class<?> clazz) {
		// eg:("SELECT name FROM USER WHERE user_id = ?", new Object[] {"1"},
		// java.lang.String.class);
		Object result;
		try {
			result = jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(clazz));
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Iterator it = rows.iterator(); while(it.hasNext()) { Map userMap = (Map)
	 * it.next(); System.out.print(userMap.get("user_id") + "\t");
	 * System.out.print(userMap.get("name") + "\t");
	 * System.out.print(userMap.get("sex") + "\t");
	 * System.out.println(userMap.get("age") + "\t"); }
	 * 
	 * @param sql
	 * @return List<Map<String,?>> map's key is table's field
	 */
	public List<?> queryList(String sql) {
		return (List<?>) jdbcTemplate.queryForList(sql);
	}
	public List<?> queryList(String sql, Class<?> clazz) {
		return (List<?>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
	}
	public List<?> queryList(String sql, Object[] params, Class<?> clazz) {
		return (List<?>) jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(clazz));
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Pager<?> findPager(Pager<?> pager, String sql) {
		return null;
	}

	public Pager<?> findPagerSuppertTag(Pager<?> pager, String sql) {
		return null;
	}

	@Override
	public Pager<?> findPager(Pager<?> pager, String sql, Object[] sqlObject) {

		return null;
	}

	@Override
	public List<?> queryList(String sql, Object[] params) {
		return jdbcTemplate.queryForList(sql, params);
	}

	@Override
	public Map<String, Object> queryForMap(String sql, Object[] params) {
		return jdbcTemplate.queryForMap(sql, params);
	}


}

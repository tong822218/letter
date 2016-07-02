package com.zm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zm.common.Pager;

@Repository("mysqlJdbcDao")
public class MySqlJdbcDaoImpl extends JdbcDaoImpl {

	private static final String QUERY_PAGE = "select * from ( %s ) _tmp limit %d,%d ";
	private static final String QUERY_COUNT = "select count(*) from ( %s ) _tmp ";
	private static final String QUERY_PAGE_SQL = "select * from ( %s ) _tmp limit ?,?";

	@SuppressWarnings("unchecked")
	public Pager<?> findPager(@SuppressWarnings("rawtypes") Pager pager, String sql) {
		if (pager.getPageNumber() == 0) {
			pager.setPageNumber(1);
		}
		String querySql = String.format(QUERY_PAGE, sql, (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
		String countSql = String.format(QUERY_COUNT, sql);
		Integer count = (Integer) queryForObject(countSql, null, Integer.class);
		pager.setList(jdbcTemplate.queryForList(querySql));
		pager.setTotalCount(count);
		return pager;
	}

	@SuppressWarnings("unchecked")
	public Pager<?> findPager(@SuppressWarnings("rawtypes") Pager pager, String sql, Object[] sqlObject) {
		String querySql = String.format(QUERY_PAGE_SQL, sql);
		String countSql = String.format(QUERY_COUNT, sql);

		int arrayLength = sqlObject.length;
		int newArrayLength = arrayLength + 2;
		Object[] newObject = new Object[newArrayLength];
		for (int i = 0; i < sqlObject.length; i++) {
			newObject[i] = sqlObject[i];
		}
		newObject[arrayLength] = (pager.getPageNumber() - 1) * pager.getPageSize();
		newObject[arrayLength + 1] = pager.getPageSize();
		Integer count = (Integer) queryForObject(countSql, sqlObject, Integer.class);
		pager.setList(jdbcTemplate.queryForList(querySql, newObject));
		pager.setTotalCount(count);
		return pager;
	}

	@SuppressWarnings("unchecked")
	public Pager<?> findPagerSuppertTag(@SuppressWarnings("rawtypes") Pager pager, String sql) {
		Pager<?> p = findPager(pager, sql);
		List<?> l = p.getList();
		Iterator<?> it = l.iterator();
		List<Object[]> result = new ArrayList<Object[]>();
		while (it.hasNext()) {
			Map<?, ?> map = (Map<?, ?>) it.next();
			result.add(map.values().toArray());
		}
		pager.setList(result);
		return pager;
	}

}

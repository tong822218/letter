package com.zm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.dao.TempDao;
import com.zm.model.Temp;
import com.zm.service.TempService;

@Service("tempService")
public class TempServiceImpl implements TempService {
	@Autowired
	private TempDao tempDao;
	@Override
	public Temp getById(String id) {
		// TODO Auto-generated method stub
		return tempDao.getById(id);
	}

	@Override
	public List<Temp> getList() {
		// TODO Auto-generated method stub
		return tempDao.getList();
	}

	@Override
	public void add(Temp temp) {
		// TODO Auto-generated method stub
		tempDao.add(temp);
	}

}

package com.zm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.dao.MsgDao;
import com.zm.model.Msg;
import com.zm.service.MsgService;

@Service("msgService")
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MsgDao msgDao;
	@Override
	public Msg getById(String id) {
		// TODO Auto-generated method stub
		return msgDao.getById(id);
	}

	@Override
	public void add(Msg msg) {
		// TODO Auto-generated method stub
		msgDao.add(msg);
	}

	@Override
	public List<Msg> getByTel(String tel) {
		// TODO Auto-generated method stub
		return msgDao.getByTel(tel);
	}

}

package com.zm.dao;

import java.util.List;

import com.zm.model.Msg;

public interface MsgDao {

	Msg getById(String id);

	void add(Msg msg);

	List<Msg> getByTel(String tel);

}

package com.zm.service;

import java.util.List;

import com.zm.model.Msg;

public interface MsgService {

	Msg getById(String id);

	void add(Msg msg);

	List<Msg> getByTel(String tel);

}

package com.zm.dao;

import java.util.List;

import com.zm.model.Temp;

public interface TempDao {

	Temp getById(String id);

	List<Temp> getList();

}

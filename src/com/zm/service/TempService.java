package com.zm.service;

import java.util.List;

import com.zm.model.Temp;

public interface TempService {

	Temp getById(String string);
	
	List<Temp> getList();

}

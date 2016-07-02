package com.zm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.dao.LetterDao;
import com.zm.model.Letter;
import com.zm.service.LetterService;

@Service("letterService")
public class LetterServiceImpl implements LetterService {
	@Autowired
	private LetterDao letterDao;
	@Override
	public void add(Letter letter) {
		letterDao.add(letter);
	}
	@Override
	public Letter getById(String id) {
		return letterDao.getById(id);
	}

}

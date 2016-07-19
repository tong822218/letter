package com.zm.service.impl;

import java.util.List;

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
	@Override
	public List<Letter> getByTel(String tel) {
		return letterDao.getByTel(tel);
	}
	@Override
	public List<Letter> getBySeller(String sellerName) {
		return letterDao.getBySeller(sellerName);
	}
	@Override
	public List<Letter> getChart(Letter letter) {
		// TODO Auto-generated method stub
		return letterDao.getChart(letter);
	}

}

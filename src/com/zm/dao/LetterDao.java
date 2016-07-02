package com.zm.dao;

import java.util.List;

import com.zm.model.Letter;

public interface LetterDao {

	void add(Letter letter);

	Letter getById(String id);

	List<Letter> getByTel(String tel);

}

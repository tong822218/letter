package com.zm.service;

import java.util.List;

import com.zm.model.Letter;

public interface LetterService {

	void add(Letter letter);

	Letter getById(String id);

	List<Letter> getByTel(String tel);

}

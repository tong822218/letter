package com.zm.dao;

import com.zm.model.Letter;

public interface LetterDao {

	void add(Letter letter);

	Letter getById(String id);

}

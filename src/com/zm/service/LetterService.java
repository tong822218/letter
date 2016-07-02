package com.zm.service;

import com.zm.model.Letter;

public interface LetterService {

	void add(Letter letter);

	Letter getById(String id);

}

package com.zm.dao;

import java.util.List;

import com.zm.model.Letter;

public interface LetterDao {

	void add(Letter letter);

	Letter getById(String id);

	List<Letter> getByTel(String tel);

	List<Letter> getBySeller(String sellerName);
	List<Letter> getBySellerName(String sellerName);

	List<Letter> getChart(Letter letter);

	List<Letter> getletterListBySeller(String sellerName);

}

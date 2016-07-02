package com.zm.util;

import javax.servlet.http.HttpServletRequest;

import com.zm.model.User;

public class Common {

	public static User getUser(HttpServletRequest request) {
		User user = ((User) request.getSession().getAttribute("user"));
		return user;
	}
}

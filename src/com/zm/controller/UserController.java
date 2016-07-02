package com.zm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {
	@RequestMapping(value = "test")
	public ModelAndView test(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		return ftl("/business/admin/vip/index", map, request);
	}
}

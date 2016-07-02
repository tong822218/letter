package com.zm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping("/Base")
public class BaseController {

	public String now(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return sdf.format(new Date());// 获取当前系统时间
	}
	public void json(HttpServletResponse response, Object text) {
		try {
			ajax(response, JSON.toJSONString(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ajax(HttpServletResponse response, String text) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(text);
		out.close();
	}

	public ModelAndView html(String url, Object obj, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != obj) {
			map.put("obj", obj);
		}
		map.put("sessionId", session.getId());
		return new ModelAndView(url, map);
	}
	
	public ModelAndView ftl(String url, Object obj, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != obj) {
			map.put("obj", obj);
		}
		map.put("sessionId", session.getId());
		return new ModelAndView(url, map);
	}
	
}
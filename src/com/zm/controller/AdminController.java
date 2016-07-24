package com.zm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zm.model.Letter;
import com.zm.model.Temp;
import com.zm.model.User;
import com.zm.service.LetterService;
import com.zm.service.TempService;
import com.zm.service.UserService;
import com.zm.taobao.API;
import com.zm.taobao.Token;
import com.zm.util.Common;
import com.zm.util.UseDate;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	private UserService userService;
	private LetterService letterService;
	private TempService tempService;

	@RequestMapping(value = "index")
	public ModelAndView index(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		User user=Common.getUser(request);
		Letter letter = new Letter();
		letter.setSeller(user.getName());
		UseDate u=new UseDate();
		letter.setCreateTime(u.get_10month());
		List<Letter> data = letterService.getChart(letter);
		
		StringBuffer month = new StringBuffer();
		StringBuffer useTimes = new StringBuffer();
		StringBuffer openTimes = new StringBuffer();
		
		for(int i = 0; i < data.size(); i++){
			month.append("'"+data.get(i).getMonth()+"',");
			useTimes.append(data.get(i).getUseTimes()+",");
			openTimes.append(data.get(i).getOpenTimes()+",");
		}
		map.put("month", month.toString());
		map.put("useTimes", useTimes.toString());
		map.put("openTimes", openTimes.toString());
		

		return html("/admin/index", map, request);

	}
	@RequestMapping(value = "cardset")
	public ModelAndView cardset(HttpServletRequest request) {
		return html("/admin/cardset", null, request);
	}
	@RequestMapping(value = "getCards")
	public void getCards(HttpServletRequest request,HttpServletResponse rsp) {
		java.util.List<Temp> temp = tempService.getList();
		json(rsp,temp);
	}
	@RequestMapping(value = "ajaxTime")
	public void ajaxTime(HttpServletRequest request,HttpServletResponse rsp) {
		Map<String,Object> map=new HashMap<String, Object>();
		User user=Common.getUser(request);
		Letter letter = new Letter();
		letter.setSeller(user.getName());
		UseDate u=new UseDate();
		String time = request.getParameter("time");
		time=u.millisToDate(Long.parseLong(time));
		letter.setCreateTime(time);
		List<Letter> data = letterService.getChart(letter);
		
		StringBuffer month = new StringBuffer();
		StringBuffer useTimes = new StringBuffer();
		StringBuffer openTimes = new StringBuffer();
		
		for(int i = 0; i < data.size(); i++){
			month.append(data.get(i).getMonth()+",");
			useTimes.append(data.get(i).getUseTimes()+",");
			openTimes.append(data.get(i).getOpenTimes()+",");
		}
		map.put("month", month.toString());
		map.put("useTimes", useTimes.toString());
		map.put("openTimes", openTimes.toString());
		json(rsp, map);

	}

	@RequestMapping(value = "hesay")
	public ModelAndView hesay(HttpServletRequest request) {
		return html("/admin/hesay", null, request);

	}

	@RequestMapping(value = "taobaoset")
	public ModelAndView taobaoset(HttpServletRequest request) {
		return html("/admin/taobaoset", null, request);

	}

	@RequestMapping(value = "sellersay")
	public ModelAndView sellersay(HttpServletRequest request) {
		User user = userService.getById(Common.getUser(request).getId());
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user", user);
		return html("/admin/sellersay", map, request);
	}

	// 如果数据库没有这个商家，就存入数据库，然后返回到前台
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		String gs = request.getParameter("code");
		Token.set(gs);
		User user = API.getCurrentUser();
		User u = userService.getById(user.getId());
		if (u == null) {
			user.setCreate_time(time);
			userService.add(user);
			u=user;
		}
		request.getSession().setAttribute("user", u);
		return new ModelAndView("redirect:/admin/index.html", null);
	}

	@RequestMapping(value = "addsellersay")
	public void addsellersay(HttpServletRequest request, HttpServletResponse rsp) {
		// HashMap<String,String> map=new HashMap<String,String>();
		// map.put("", value);
		User user = userService.getById(((User)request.getSession().getAttribute("user")).getId());
		if (user != null) {
			user.setSeller_say(request.getParameter("content"));
			userService.update(user);
			json(rsp, "更新成功");
		} else {
			json(rsp, "更新失败");
		}

	}
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LetterService getLetterService() {
		return letterService;
	}
	@Autowired
	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}

	public TempService getTempService() {
		return tempService;
	}
	@Autowired
	public void setTempService(TempService tempService) {
		this.tempService = tempService;
	}

	
}

package com.zm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zm.model.User;
import com.zm.service.UserService;
import com.zm.taobao.API;
import com.zm.taobao.Token;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	
	private UserService userService;
	
	@RequestMapping(value = "index")
	public ModelAndView index(HttpServletRequest request){
		return html("/admin/index", null, request);
        
	}
	
	@RequestMapping(value = "hesay")
	public ModelAndView hesay(HttpServletRequest request){
		return html("/admin/hesay", null, request);
        
	}
	
	@RequestMapping(value = "taobaoset")
	public ModelAndView taobaoset(HttpServletRequest request){
		return html("/admin/taobaoset", null, request);
        
	}
	@RequestMapping(value = "sellersay")
	public ModelAndView sellersay(HttpServletRequest request){
		return html("/admin/sellersay", null, request);
	}
	
	//如果数据库没有这个商家，就存入数据库，然后返回到前台
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse rsp) {
		String gs= request.getParameter("code");
		Token.set(gs);
		User user = API.getCurrentUser();
		User u = userService.getById(user.getId());
		if(u==null){
			userService.add(user);
		}
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("user", user);
		return new ModelAndView("redirect:/admin/index.html",map);
	}
	
	@RequestMapping(value = "addsellersay")
	public void addsellersay(HttpServletRequest request,HttpServletResponse rsp){
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("", value);
		User user=userService.getById("");
		if(user!=null){
			user.setSeller_say(request.getParameter("content"));
			userService.update(user);
			json(rsp, "更新成功");
		}else{
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

}

package com.zm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.zm.model.Letter;
import com.zm.model.Msg;
import com.zm.model.Temp;
import com.zm.service.LetterService;
import com.zm.service.TempService;

@Controller
@RequestMapping("/l")
public class LetterController extends BaseController {

	private TempService tempService;
	private LetterService letterService;
	
	
	@RequestMapping(value = "home")
	public ModelAndView toSlider(HttpServletRequest request){
		return html("/slider/slider", null, request);
        
	}
	@RequestMapping(value = "temp/{id}")
	public ModelAndView toTemp(@PathVariable("id") String id,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Temp temp=tempService.getById(id);
		if(temp==null)
			return html("/slider/slider", null, request);
		map.put("tempid", temp.getId());
		return html(temp.getTemp_url(), map, request);
        
	}
	
	@RequestMapping(value = "temp/getList")
	public void getTempList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		java.util.List<Temp> temp = tempService.getList();
		json(response,temp);
	}
	@RequestMapping(value = "save")
	public ModelAndView saveLetter(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	    Map map = request.getParameterMap();
		Letter letter = new Letter();
		letter.setId(UUID.randomUUID().toString());
		letter.setTemp(request.getParameter("tempid").toString());
		letter.setParams(JSON.toJSONString(map).replace("[", "").replace("]", ""));
//		letter.setImgs(JSON.toJSONString(map));
		letter.setCreateTime(now());
		HttpSession session = request.getSession();
		session.setAttribute("letter", letter);

		return html("/letter/send", map, request);
	}
	@RequestMapping(value = "send")
	public ModelAndView sendLetter(HttpServletRequest request){
		String tel=request.getParameter("tel");
		String sender=request.getParameter("sender");
		HttpSession session = request.getSession();
		Letter letter = (Letter) session.getAttribute("letter");
		
		Map<String, Object> map = new HashMap<String, Object>();
		return html("/msg/result", map, request);
	}
	@RequestMapping(value = "go/{id}")
	public ModelAndView toLetter(@PathVariable("id") String id,HttpServletRequest request){

		Letter letter=letterService.getById(id);
		Temp temp=tempService.getById(letter.getTemp());
		return html(temp.getUrl(), JSON.parseObject(letter.getParams()), request);
	}
	@RequestMapping(value = "r")
	public ModelAndView checkTel(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String tel=request.getParameter("tel");
		Temp temp=tempService.getById("");
		return html(temp.getUrl(), map, request);
	}
	@RequestMapping(value = "s")
	public ModelAndView toFillReceiver(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String tempid=request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("tempid", tempid);
		return html("/send", map, request);
	}
	@RequestMapping(value = "send")
	public ModelAndView fillReceiver(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String tel=request.getParameter("tel");
		String sender=request.getParameter("sender");
		HttpSession session = request.getSession();
		session.setAttribute("tel", tel);
		session.setAttribute("sender", sender);
		String tempid=session.getAttribute("tempid").toString();
		Temp temp=tempService.getById(tempid);
		return html(temp.getTemp_url(), map, request);
	}
	
	
	
	
	public TempService getTempService() {
		return tempService;
	}
	@Autowired
	public void setTempService(TempService tempService) {
		this.tempService = tempService;
	}
	public LetterService getLetterService() {
		return letterService;
	}
	@Autowired
	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}
}

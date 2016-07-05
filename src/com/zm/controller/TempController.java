package com.zm.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zm.model.Temp;
import com.zm.service.TempService;

@Controller
@RequestMapping("/t")
public class TempController extends BaseController {

	private TempService tempService;

	@RequestMapping(value = "toaddtemp")
	public ModelAndView toAddTemp(HttpServletRequest request){
		
		return html("/system/addtemp", null, request);
	}
	@RequestMapping(value = "addtemp")
	public void addTemp(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String id=request.getParameter("id");
		if(id==null||id.equals(""))
			id=UUID.randomUUID().toString();
		String title=request.getParameter("title");
		String url=request.getParameter("url");
		String tempurl=request.getParameter("tempurl");
		String img=request.getParameter("img");
		Temp temp=new Temp();
		temp.setId(id);
		temp.setTitle(title);
		temp.setUrl(url);
		temp.setTemp_url(tempurl);
		temp.setImg_path(img);
		temp.setCreate_time(now());
		tempService.add(temp);
		json(response,1);
	}
	
	public TempService getTempService() {
		return tempService;
	}
	@Autowired
	public void setTempService(TempService tempService) {
		this.tempService = tempService;
	}
	
}

package com.zm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.zm.model.Msg;
import com.zm.service.MsgService;

@Controller
@RequestMapping("/m")
public class MsgController extends BaseController {

	private MsgService msgService;
	@RequestMapping(value = "s")
	public ModelAndView toSendMsg(HttpServletRequest request){
		
		return html("/msg/send", null, request);
	}
	@RequestMapping(value = "send")
	public ModelAndView sendMsg(HttpServletRequest request){
		String content=request.getParameter("content");
		String tel=request.getParameter("tel");
		String sender=request.getParameter("sender");
		Msg msg=new Msg();
		String id=UUID.randomUUID().toString();
		msg.setId(id);
		msg.setSender(sender);
		msg.setContent(content);
		msg.setTel(tel);
		msg.setCreateTime(now());
		msgService.add(msg);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return html("/msg/result", map, request);
	}
	@RequestMapping(value = "r")
	public ModelAndView toCheckTel(HttpServletRequest request){
		return html("/msg/check", null, request);
	}
	
	
	@RequestMapping(value = "read")
	public ModelAndView toMsg(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String id=request.getParameter("id");
		if(id==null||id.trim().equals(""))
		{
			map.put("sender", "卓梦网络科技");
			map.put("content", "这是一封来自远方的情书，祝福你，亲爱的");
			return html("/msg/read", map, request);
		}
			
		Msg msg=msgService.getById(id);
		if(msg!=null){
			map.put("sender", msg.getSender());
			map.put("content", msg.getContent());
			return html("/msg/read", map, request);
		}else{
			List<Msg> msgs=msgService.getByTel(id);
			if(msgs==null||msgs.size()==0){
				map.put("message", "<script type='text/javascript'>alert('暂时还没有您的留言哦，可能是送您礼物的人想亲口告诉您，赶快问问吧！也许还有惊喜呢……')</script>");
				return html("/msg/check", map, request);
			}else if(msgs.size()==1){
				map.put("sender", msgs.get(0).getSender());
				map.put("content", msgs.get(0).getContent());
				return html("/msg/read", map, request);
			}else{
				map.put("msgs", msgs);
				return html("/msg/list", map, request);
			}
		}
		
	}
	public MsgService getMsgService() {
		return msgService;
	}
	@Autowired
	public void setMsgService(MsgService msgService) {
		this.msgService = msgService;
	}
	
	
}

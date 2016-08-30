package com.zm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zm.model.Letter;
import com.zm.model.Temp;
import com.zm.model.User;
import com.zm.service.LetterService;
import com.zm.service.TempService;
import com.zm.service.UserService;
import com.zm.util.Common;
import com.zm.util.HtmlUtil;

@Controller
@RequestMapping("/l")
public class LetterController extends BaseController {

	private TempService tempService;
	private LetterService letterService;
	private UserService userService;
	
	@RequestMapping(value = "slide")
	public ModelAndView slide(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		return html("/slider/index", map, request);
	}
	@RequestMapping(value = "home")
	public ModelAndView toSlider(HttpServletRequest request){
//		String name = request.getParameter("seller");
		String sellerid = request.getParameter("sellerid");
//		if(sellerid.length()>20){
//			User u = userService.getByToken(sellerid);
//			
//			if(u!=null){
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date now = new Date();
//				long nows = now.getTime();
//				long cres = 0;
//				String time = u.getCreate_time();
//				if(null!=time && !time.equals("")){
//					try {
//						Date ti = sdf.parse(time);
//						cres=ti.getTime();
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					long sevenDay = 60*60*24*1000*7;
//					if((nows-cres)>sevenDay){
//						return html("/slider/404", null, request);
//					}else{
//						Map<String, Object> map=new HashMap<String, Object>();
//						map.put("sellerSay", u.getSeller_say());
//						return html("/slider/slider", map, request);
//					}
//				}else{
//					u.setCreate_time(sdf.format(now));
//					userService.update(u);
//					Map<String, Object> map=new HashMap<String, Object>();
//					map.put("sellerSay", u.getSeller_say());
//					return html("/slider/slider", map, request);
//				}
//				
//			}
//			return null;
//		}else{
//			User user = userService.getByName(name);
			User user = userService.getById(sellerid);
			Map<String, Object> map=new HashMap<String, Object>();
			if(user!=null){
				map.put("sellerSay", user.getSeller_say());
			}
//			request.getSession().setAttribute("seller", name);
			request.getSession().setAttribute("sellerid", sellerid);
			return html("/slider/slider", map, request);
//		}
		
        
	}
	@RequestMapping(value = "open")
	public ModelAndView open(HttpServletRequest request) {
		return html("/slider/open", null, request);

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
		List<Temp> temp = tempService.getList();
		List<Temp> list=new ArrayList<Temp>();
		String userid = (String)request.getSession().getAttribute("sellerid");
		if(userid!=null){
			User user = userService.getById(userid);
			String cards = user.getSel_cards();
			String[] cardss = cards.split(";");
			for(int i=0;i<temp.size();i++){
				for(int j=0;j<cardss.length;j++){
					if(temp.get(i).getId().equals(cardss[j])){
						list.add(temp.get(i));
					}
				}
			}
		}
		json(response,list);
	}
	@RequestMapping(value = "save")
	public void saveLetter(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
	    Map map = request.getParameterMap();
		Letter letter = new Letter();
		letter.setId(UUID.randomUUID().toString());
		letter.setTemp(request.getParameter("tempid").toString());
		letter.setParams(JSON.toJSONString(map).replace("[", "").replace("]", ""));
//		letter.setImgs(JSON.toJSONString(map));
		letter.setCreateTime(now());
		HttpSession session = request.getSession();
		
		session.setAttribute("letter", letter);
		json(response, 1);
	}
	@RequestMapping(value = "s")
	public ModelAndView toSend(HttpServletRequest request){
		
		return html("/letter/sendnew", null, request);
	}
	@RequestMapping(value = "send")
	public void send(HttpServletRequest request,HttpServletResponse response){
			String tel=request.getParameter("tel");
			String sender=request.getParameter("sender");
			String senderTel=request.getParameter("senderTel");
			HttpSession session = request.getSession();
			Letter letter = (Letter) session.getAttribute("letter");
			if(letter==null){
				//return html("/slider/slider", null, request);
			}
			String sellerid=(String) session.getAttribute("sellerid");
			letter.setSeller(sellerid);
			letter.setTel(tel);
			letter.setSender(sender);
			letter.setSenderTel(senderTel);
			letterService.add(letter);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", letter.getId());
			json(response, 1);
	}
	@RequestMapping(value = "read")
	public ModelAndView read(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String id=request.getParameter("id");
		if(id==null||id.trim().equals(""))
		{
			map.put("sender", "卓梦网络科技");
			map.put("content", "这是一封来自远方的情书，祝福你，亲爱的");
			return html("/msg/read", map, request);
		}
			
		List<Letter> letters = letterService.getByTel(id);
		if(letters==null||letters.size()==0){
			Letter letter = letterService.getById(id);
			if(letter==null){
				map.put("message", "<script type='text/javascript'>alert('暂时还没有您的留言哦，可能是送您礼物的人想亲口告诉您，赶快问问吧！也许还有惊喜呢……')</script>");
				return html("/letter/check", map, request);
			}
			Temp temp=tempService.getById(letter.getTemp());
			return html(temp.getUrl(), JSON.parseObject(letter.getParams()), request);
			//return html(temp.getUrl(), JSON.parseObject(HtmlUtil.filterHtml(letter.getParams())), request);
		}else if(letters.size()==1){
			Temp temp=tempService.getById(letters.get(0).getTemp());
			return html(temp.getUrl(), JSON.parseObject(letters.get(0).getParams()), request);
			//return html(temp.getUrl(), JSON.parseObject(HtmlUtil.filterHtml(letters.get(0).getParams())), request);
		}else{
			map.put("letters", letters);
			return html("/letter/list", map, request);
		}
		
	}
	@RequestMapping(value = "r")
	public ModelAndView checkTel(HttpServletRequest request){
		return html("/letter/check", null, request);
	}

	@RequestMapping(value = "go/{id}")
	public ModelAndView toLetter(@PathVariable("id") String id,HttpServletRequest request){

		Letter letter=letterService.getById(id);
		Temp temp=tempService.getById(letter.getTemp());
		return html(temp.getUrl(), JSON.parseObject(letter.getParams()), request);
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
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}

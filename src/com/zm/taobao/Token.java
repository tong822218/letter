package com.zm.taobao;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

import com.taobao.api.internal.util.WebUtils;

import net.sf.json.JSONObject;

public class Token {
	public static String token = "";
	public static String refresh_token = "";

	public static void set(String code) {
		// https://oauth.taobao.com/authorize?response_type=code&client_id=23232930&redirect_uri=http://127.0.0.1:8080/shoesize/taobao/index&state=1212&view=web

		String url ="https://oauth.taobao.com/token";
		String appkey = "23293480";
		String secret = "7a2f7cac57e40e0f570b7bf54bdd41d5";
		String redirect_uri = "http://192.168.31.104:8080/letter/admin/login.html";
		//String redirect_uri = "https://letter.ews.m.jaeapp.com/admin/login.html";

		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		props.put("code", code);
		props.put("client_id", appkey);
		props.put("client_secret", secret);
		props.put("redirect_uri", redirect_uri);

		props.put("view", "web");
		String json_text = "";
		try {
			WebUtils.setIgnoreSSLCheck(true);
			json_text = WebUtils.doPost(url, props, 30000, 30000);
			JSONObject obj = JSONObject.fromObject(json_text);
			// access_token
			token = obj.get("access_token").toString();
			// Access token的类型目前只支持bearer
			obj.get("token_type").toString();
			// Access token过期时间
			obj.get("expires_in").toString();
			// Refresh token
			obj.get("refresh_token").toString();
			// Refresh token过期时间
			obj.get("re_expires_in").toString();
			// r1级别API或字段的访问过期时间
			obj.get("r1_expires_in").toString();
			// r2级别API或字段的访问过期时间
			obj.get("r2_expires_in").toString();
			// w1级别API或字段的访问过期时间
			obj.get("w1_expires_in").toString();
			// w2级别API或字段的访问过期时间
			obj.get("w2_expires_in").toString();
			// 淘宝账号
			URLDecoder.decode(obj.get("taobao_user_nick").toString(), "UTF-8");
			// 淘宝帐号对应id
			obj.get("taobao_user_id").toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get() {
		return token;
	}
}

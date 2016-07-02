package com.zm.model;

import java.util.Date;


public class User {
	private String id="";
	private String username="";
	private String password="";
	private String name="";
	private String token="";
	private String create_time;
	private String seller_say="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSeller_say() {
		return seller_say;
	}
	public void setSeller_say(String seller_say) {
		this.seller_say = seller_say;
	}
	
}

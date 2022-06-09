package com.jwt.model;

public class JwtREquest {
	
	private String username;
	private String password;
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
	public JwtREquest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public JwtREquest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JwtREquest [username=" + username + ", password=" + password + "]";
	}
	
	
}

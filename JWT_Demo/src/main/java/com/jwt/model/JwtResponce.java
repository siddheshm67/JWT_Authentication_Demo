package com.jwt.model;

public class JwtResponce {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponce(String token) {
		super();
		this.token = token;
	}

	public JwtResponce() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtResponce [token=" + token + "]";
	}
	
	
}

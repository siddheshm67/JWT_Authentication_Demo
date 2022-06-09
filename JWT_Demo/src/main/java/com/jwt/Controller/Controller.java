package com.jwt.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("/get")
	public String get() {
		
		return"this is responce from get method";
	}
	
	@RequestMapping("/request")
	public String myRequest() {
		return "this is second request";
	}
}

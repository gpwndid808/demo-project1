package com.injeinc.demo_project1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.injeinc.demo_project1.service.DemoService;

@RestController
public class DemoController {
	
	private DemoService demoservice;
	
	@GetMapping("/test1")
	public String test1() throws Exception {
		return demoservice.test1();
	}
	
	@GetMapping("/test2")
	public String test2() throws Exception {
		
		return "dd";
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test3";
	}
	
	@GetMapping("/test4")
	public String test4() {
		return "test4";
	}
}

package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ListService;

@Controller
public class MainController {
	@Autowired
	ListService listservice;

	@RequestMapping("/")
	public String main (Model model){
		System.out.println("list 출력");
		
		return "login";
	}
	
	
}

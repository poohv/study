package com.spp.p08001;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


public class InsertBook implements execute {
	@Override
	public void Bexecute(Model model) {
		System.out.println("insertBook 시작");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		int isbn = (Integer.parseInt(request.getParameter("isbn")));
		int price = (Integer.parseInt(request.getParameter("price")));
		System.out.println(name);
		BDao bdao = new BDao();
		bdao.insert(name, author, isbn, price);
	}


	
	

}

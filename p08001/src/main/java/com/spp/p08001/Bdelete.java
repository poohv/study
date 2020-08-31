package com.spp.p08001;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class Bdelete implements execute {

	@Override
	public void Bexecute(Model model) {
		System.out.println("삭제 서비스 실행");
		BDao bdao = new BDao();
		Map <String, Object> map  = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int isbn = Integer.parseInt( request.getParameter("isbn"));
		System.out.println(isbn);
		bdao.delete(isbn);
		
		
		
	}

}

package com.spp.p08001;

import java.util.ArrayList;

import org.springframework.ui.Model;

public class Booklist implements execute {

	@Override
	public void Bexecute(Model model) {
		BDao  bdao = new BDao();
		ArrayList<Bdto> list = bdao.select();
		model.addAttribute("list",list);
		
	}

}

package com.spp.p08001;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BController {
	execute bexcute;
	@RequestMapping("/")
	public String start(Model model) {
		System.out.println("시작합니다");
		bexcute = new Booklist();
		bexcute.Bexecute(model);
		return "book/viewpage";	
		
		
	};
	@RequestMapping("/register")
	public String Register(Model model , HttpServletRequest request) {
		System.out.println("책을 등록 합니다.");
		bexcute = new InsertBook();
		model.addAttribute("request", request);
		bexcute.Bexecute(model);
		return "redirect:/book/list";
	}
	
	@RequestMapping("/list")
	public String read(Model model )  {
		System.out.println("list 실행 합니다");
		bexcute = new Booklist();
		bexcute.Bexecute(model);
					
		return "book/viewpage";
	}
	
	@RequestMapping("/del")
	public String del(Model model , HttpServletRequest request)  {
		System.out.println("del 실행 합니다");
		/*String text = request.getParameter("isbn");
		System.out.println(text);*/
		model.addAttribute("request", request);
		bexcute = new Bdelete();
		bexcute.Bexecute(model);
					
		return "redirect:/book/list";
	}
	
}

package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	  @Inject
	  private BoardService service;
	  
	  //입력,조회
	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public void registerGet(BoardVO board, Model model) {
		  logger.info("register...");
		  
	  }
	  
	  @RequestMapping(value = "/register" ,method = RequestMethod.POST)
	  public String registPost(BoardVO board,RedirectAttributes rttr) throws Exception {
		  
		  logger.info("regist post....");
		  logger.info(board.toString());
		  
		  service.regist(board);
		  
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  
		  return "redirect:/board/listAll";
		  
	  }
	  
	  @RequestMapping(value = "/listAll" ,method = RequestMethod.GET)
	  public void listAll(Model model) throws Exception {
		  logger.info("show all list.........");
		  model.addAttribute("list", service.listAll());
		  
	  }
	  
	  @RequestMapping(value = "/read", method =RequestMethod.GET )
	  public void read (@RequestParam("bno") int bno,	Model model) throws Exception {
		  model.addAttribute(service.read(bno));
		  
	  } 
	  
	  
	  @RequestMapping(value = "/remove", method = RequestMethod.POST)
	  public String remove(@RequestParam("bno") int bno , RedirectAttributes rttr) throws Exception {
		  
		  service.remove(bno);
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  
		  return "redirect:/board/listAll";
	  }
	  
	  
	  @RequestMapping(value = "/modify", method = RequestMethod.GET )
	  public void modifyget(int bno, Model model) throws Exception {
		  model.addAttribute(service.read(bno));
	  }
	  
	  
	  @RequestMapping(value = "/modify", method=RequestMethod.POST )
	  public String modifyPOST(BoardVO board,RedirectAttributes rttr) throws Exception {
		  
		  logger.info("mod start.....");
		  service.modify(board);
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  
		  return "redirect:/board/listAll";
	  }
	  

}

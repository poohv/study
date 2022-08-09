package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
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
	  
	  //�엯�젰,議고쉶
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
	  
	  @RequestMapping(value = "/listCri" ,method = RequestMethod.GET)
	  public void listAll(Criteria cri,Model model) throws Exception {
		  logger.info("show all list.........");
		  model.addAttribute("list", service.listCriteria(cri));
		  
	  }
	  
	  @RequestMapping(value = "/readPage", method =RequestMethod.GET )
	  public void read (@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		  model.addAttribute(service.read(bno));
		  
	  } 
	  
	  
	  @RequestMapping(value = "/removePage", method = RequestMethod.POST)
	  public String remove(@RequestParam("bno") int bno , Criteria cri , RedirectAttributes rttr) throws Exception {
		  
		  service.remove(bno);
		  
		  //삭제 결과는 임시로 사용하는 데이터이므로 addFlashAttribute 사용
		  rttr.addAttribute("page",cri.getPage());
		  rttr.addAttribute("perPageNum",cri.getPerPageNum());
		 
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  
		  return "redirect:/board/listPage";
	  }
	  
	  
	  @RequestMapping(value = "/modify", method = RequestMethod.GET )
	  public void modifyget(int bno, @ModelAttribute("cri") Criteria cri , Model model) throws Exception {
		  model.addAttribute(service.read(bno));
	  }
	  
	  
	  @RequestMapping(value = "/modify", method=RequestMethod.POST )
	  public String modifyPOST(BoardVO board,Criteria cri,RedirectAttributes rttr) throws Exception {
		  
		  logger.info("mod start.....");
		  service.modify(board);
		  
		  rttr.addAttribute("page",cri.getPage());
		  rttr.addAttribute("perPageNum",cri.getPerPageNum());
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  
		  return "redirect:/board/listPage";
	  }
	  
	  @RequestMapping(value = "/listPage", method = RequestMethod.GET)
	  public void listPage(Criteria cri, Model model) throws Exception {

	    logger.info(cri.toString());

	    model.addAttribute("list", service.listCriteria(cri));
	    PageMaker pageMaker = new PageMaker();
	    
	    pageMaker.setCri(cri);
	    // pageMaker.setTotalCount(131);

	    pageMaker.setTotalCount(service.listCountCriteria(cri));

	    model.addAttribute("pageMaker", pageMaker);
	  }
	  
	  

}

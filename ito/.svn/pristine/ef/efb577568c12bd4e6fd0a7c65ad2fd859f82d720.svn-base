package kr.co.kyobo.bill.rate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.bill.rate.service.RateService;

/**
 * 계약금액관리 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 7. 14. 최초작성 - 김태연
 * </pre>
 *
 * @author 김태연
 * @version 1.0 최초작성
 */
@Controller
public class RateController {

	@Autowired
	private RateService rateService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/rate/index")
	public String indexPage(Model model) {
		return "thymeleaf/bill/rate/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/rate/list")
	public String listPage(Model model) {
		return "thymeleaf/bill/rate/list";
	}
	
	@ResponseBody
	@RequestMapping("/bill/rate/findAll")
	public Map<String, Object> findAll(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = rateService.findAll(params);
		
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/rate/findOne")
	public Map<String, Object> findOne(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = rateService.findOne(params);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/rate/findAmount")
	public int findAmount(@RequestBody Map<String, Object> params) {
		Integer amount = rateService.findAmount(params);
		return amount == null ? 0 : amount;
	}
	
	@ResponseBody
	@RequestMapping("/bill/rate/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
			, Authentication authentication) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		int resultCnt = 0;
		
		// 원본 데이터 등록여부 체크
		if("N".equals(params.get("BASE_YN"))) {
			Map<String, Object> baseCheckMap = new HashMap<String, Object>();
			baseCheckMap.putAll(params);
			baseCheckMap.put("BASE_YN", "Y");
			resultCnt = rateService.findCount(baseCheckMap);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.base.data.fail", new Object[] {"요율"}, null));
				
				return resultMap;
			}
		}
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		resultCnt = rateService.insert(params);
			
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
		}
		
		return resultMap;
	}
}

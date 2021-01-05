package kr.co.kyobo.hst.contractHst.controller;

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

import kr.co.kyobo.bill.contract.service.ContractAmountService;
import kr.co.kyobo.common.service.CommonCodeService;
import kr.co.kyobo.hst.contractHst.service.ContractAmountHstService;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class ContractAmountHstController {

	@Autowired
	private ContractAmountHstService contractAmountHstService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/hst/contractAmountHst/index")
	public String indexPage(Model model) {
		return "thymeleaf/hst/contractAmountHst/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/hst/contractAmountHst/list")
	public String listPage(Model model) {
		return "thymeleaf/hst/contractAmountHst/list";
	}
	
	@ResponseBody
	@RequestMapping("/hst/contractAmountHst/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int totalCnt = contractAmountHstService.findCount(params);
		List<Map<String, Object>> dataList = contractAmountHstService.findAll(params);
		
		resultMap.put("recordsTotal", totalCnt);
		resultMap.put("recordsFiltered", totalCnt);
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
}

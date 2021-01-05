package kr.co.kyobo.bill.contract.controller;

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
public class ContractAmountController {

	@Autowired
	private ContractAmountService contractAmountService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contractAmount/index")
	public String indexPage(Model model) {
		return "thymeleaf/bill/contractAmount/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contractAmount/list")
	public String listPage(Model model) {
		return "thymeleaf/bill/contractAmount/list";
	}
	
	@ResponseBody
	@RequestMapping("/bill/contractAmount/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = contractAmountService.findAll(params);
		
		resultMap.put("recordsTotal", dataList.size());
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/contractAmount/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
			, Authentication authentication) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		List<Map<String, Object>> amountList = new ArrayList<Map<String, Object>>();
		String[] svcMonthArr = {};
		String[] amountArr = {};
		
		if(!StringUtils.isEmpty(params.get("SVC_MONTH"))) {
			svcMonthArr = params.get("SVC_MONTH").toString().split(",", -1);
		}
		if(!StringUtils.isEmpty(params.get("AMOUNT"))) {
			amountArr = params.get("AMOUNT").toString().split(",", -1);
		}
		
		for (int i = 0; i < svcMonthArr.length; i++) {
			Map<String, Object> amountMap = new HashMap<String, Object>();
			amountMap.put("CONTRACT_TYPE_CD", params.get("CONTRACT_TYPE_CD"));
			amountMap.put("CONTRACT_NO", params.get("CONTRACT_NO"));
			amountMap.put("SVC_MONTH", svcMonthArr[i]);
			amountMap.put("AMOUNT", amountArr[i]);
			amountMap.put("REG_USER_ID", userDetails.getUsername());
			
			amountList.add(amountMap);
		}
		
		if(amountList.size() > 0) {
			params.put("amountList", amountList);
			
			int resultCnt = contractAmountService.insert(params);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
			}
		}
		
		return resultMap;
	}
}

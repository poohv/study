package kr.co.kyobo.bill.contract.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.bill.contract.service.ContractService;
import kr.co.kyobo.common.service.CommonCodeService;
import kr.co.kyobo.common.service.SftpService;
import lombok.extern.slf4j.Slf4j;

/**
 * 계약관리 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 7. 2. 최초작성 - 김태연
 * </pre>
 *
 * @author 김태연
 * @version 1.0 최초작성
 */
@Controller
@Slf4j
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private SftpService sftpService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contract/index")
	public String indexPage(Model model) {
		return "thymeleaf/bill/contract/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contract/list")
	public String listPage(Model model) {
		return "thymeleaf/bill/contract/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contract/regist")
	public String registPage(Model model) {
		return "thymeleaf/bill/contract/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/contract/update")
	public String updatePage(@RequestParam Map<String, Object> params, Model model) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		conditions.put("DOMAINID", "CONTRACT_TYPE");
		model.addAttribute("contractTypeCdList", commonCodeService.findCodeList(conditions));
		model.addAllAttributes(params);
		
		return "thymeleaf/bill/contract/update";
	}
	
	@ResponseBody
	@RequestMapping("/bill/contract/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int totalCnt = contractService.findCount(params);
		List<Map<String, Object>> dataList = contractService.findAll(params);
		
		resultMap.put("recordsTotal", totalCnt);
		resultMap.put("recordsFiltered", totalCnt);
		resultMap.put("data", dataList);
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//		objectMapper.writeValue(new File("C:/dev/data/test.json"), resultMap);
		
		//JSch로 원격업로드
//		sftpService.init();
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/contract/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return contractService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/bill/contract/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
			, Authentication authentication
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		int resultCnt = contractService.findCount(params);
		
		if(resultCnt > 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.exist.data", null, null));
		} else {
			resultCnt = contractService.insert(params);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
			}
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/contract/updateData")
	public Map<String, Object> update(@RequestBody Map<String, Object> params
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("UPD_USER_ID", userDetails.getUsername());
		
		int resultCnt = contractService.update(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
		}
		
		return resultMap;
	}
}

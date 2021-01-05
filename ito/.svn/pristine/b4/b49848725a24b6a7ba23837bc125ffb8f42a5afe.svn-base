package kr.co.kyobo.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kyobo.bill.resource.controller.ResourceController;
import kr.co.kyobo.common.service.CommonCodeService;
import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@RequestMapping("/api/code")
public class CommonCodeController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseBody
	@RequestMapping("/{DOMAINID}")
	public List<Map<String, Object>> collect(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.findCodeList(params);
	}
	
	@ResponseBody
	@RequestMapping("/group")
	public List<Map<String, Object>> group(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.findList(params);
	}
	
	@ResponseBody
	@RequestMapping("/baseLineSeq")
	public List<Map<String, Object>> baseLineSeq(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.baseLineSeq(params);
	}
	
	@ResponseBody
	@RequestMapping("/rateSeq")
	public List<Map<String, Object>> rateSeq(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.rateSeq(params);
	}
	
	@ResponseBody
	@RequestMapping("/contractAmountSeq")
	public List<Map<String, Object>> contractAmountSeq(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.contractAmountSeq(params);
	}
	
	@ResponseBody
	@RequestMapping("/detailSeq")
	public List<Map<String, Object>> detailSeq(@PathVariable Map<String, Object> vals
			, @RequestParam Map<String, Object> params
			, Model model) {
		params.putAll(vals);
		return commonCodeService.detailSeq(params);
	}
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/common/code/index")
	public String indexPage(Model model) {
		return "thymeleaf/common/code/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/common/code/list")
	public String listPage(Model model) {
		return "thymeleaf/common/code/list";
	}
	
	@ResponseBody
	@RequestMapping("/common/code/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!"-1".equals(params.get("length"))){
			int totalCnt = commonCodeService.findCount(params);
			List<Map<String, Object>> dataList = commonCodeService.findAll(params);
				
			resultMap.put("recordsTotal", totalCnt);
			resultMap.put("recordsFiltered", totalCnt);
			resultMap.put("data", dataList);
		}else {
			List<Map<String, Object>> dataList = commonCodeService.findAll(params);
			resultMap.put("recordsTotal", dataList.size());
			resultMap.put("recordsFiltered", dataList.size());
			resultMap.put("data", dataList);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/common/code/updateOrderNo")
	public Map<String, Object> updateOrderNo(@RequestBody String[] domainList
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		Map<String, Object> params = new HashMap<String, Object>();
		for (int i = 0; i < domainList.length; i++) {
			params.put("VALUE", domainList[i]);
			params.put("DOMAIN_SEQ", i);
			int resultCnt = commonCodeService.updateOrderNo(params);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
			}
		}
		
		return resultMap;
	}
}

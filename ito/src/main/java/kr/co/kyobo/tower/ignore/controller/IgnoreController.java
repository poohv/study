package kr.co.kyobo.tower.ignore.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import kr.co.kyobo.common.service.CommonCodeService;
import kr.co.kyobo.tower.ignore.service.IgnoreService;
import lombok.extern.slf4j.Slf4j;

/**
 * 성능집계 예외일자 관리 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 7. 29. 최초작성 - 최지웅
 * </pre>
 *
 * @author 최지웅
 * @version 1.0 최초작성
 */
@Controller
@Slf4j
public class IgnoreController {

	@Autowired
	private IgnoreService ignoreService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/ignore/index")
	public String indexPage(Model model) {
		return "thymeleaf/tower/ignore/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/ignore/list")
	public String listPage(Model model) {
		return "thymeleaf/tower/ignore/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/ignore/regist")
	public String registPage(Model model) {
		return "thymeleaf/tower/ignore/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/ignore/update")
	public String updatePage(@RequestParam Map<String, Object> params, Model model) {
		
		//String baselineId = params.get(arg0)
		params.put("BASELINE_ID", params.get("BASELINE_ID"));
		
		Map<String, Object> conditions1 = new HashMap<String, Object>();
		conditions1.put("DOMAINID", "RESOURCE_TYPE");
		model.addAttribute("resourceTypeCdList", commonCodeService.findCodeList(conditions1));
		
		Map<String, Object> conditions2 = new HashMap<String, Object>();
		conditions2.put("DOMAINID", "TOWER_TYPE");
		model.addAttribute("towerTypeCdList", commonCodeService.findCodeList(conditions2));
		
		Map<String, Object> conditions3 = new HashMap<String, Object>();
		conditions3.put("DOMAINID", "REPORT_CATEGORY");
		model.addAttribute("reportCategoryCdList", commonCodeService.findCodeList(conditions3));
		
		Map<String, Object> conditions4 = new HashMap<String, Object>();
		conditions4.put("DOMAINID", "RESOURCE_CATEGORY");
		model.addAttribute("resourceCategoryCdList", commonCodeService.findCodeList(conditions4));
		
		model.addAllAttributes(params);
		
		return "thymeleaf/tower/ignore/update";
	}
	
	@ResponseBody
	@RequestMapping("/tower/ignore/dataList")
	public Map<String, Object> findDataList(@RequestBody Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = ignoreService.findAll(params);
			
		resultMap.put("recordsTotal", dataList.size());
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		resultMap.put("SEARCH_MONTH", params.get("INPUT_DATE"));
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/ignore/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return ignoreService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/tower/ignore/saveIgnoreDate")
	public Map<String, Object> saveIgnoreDate(@RequestBody Map<String, Object> params
			, Authentication authentication
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String[] dataArrayIgnoreDate = {};
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		if(!StringUtils.isEmpty(params.get("IGNORE_DATE"))) {
			dataArrayIgnoreDate = params.get("IGNORE_DATE").toString().split(",", -1);
		}
		
		for (int i = 0; i < dataArrayIgnoreDate.length; i++) {
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("IGNORE_DATE", dataArrayIgnoreDate[i]);
			groupMap.put("USE_YN", "Y");
			groupMap.put("REMARK", "");
			groupMap.put("REG_USER_ID", userDetails.getUsername());
			groupMap.put("UPD_USER_ID", userDetails.getUsername());
			
			dataList.add(groupMap);
		}
		
		params.put("dataList", dataList);
		params.put("dataListCnt", dataList.size());
		int resultCnt = ignoreService.saveIgnoreDate(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/ignore/findDate")
	public Map<String, Object> findDate(@RequestBody Map<String, Object> params
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		String startMM = params.get("INPUT_DATE").toString();
		String year = startMM.substring(0, 4);
		String month = startMM.substring(4, 6);
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		
		String satirday = "";
		String sunday = "";
		
		ArrayList<String> dateList1 = new ArrayList<>();
		ArrayList<String> dateList2 = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		cal.set(yyyy, mm-1, 1);
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for(int i=1; i <maxDate+1; i++) {
			cal.clear();
			
			cal.set(yyyy, mm-1, i);
			
			switch(cal.get(cal.DAY_OF_WEEK)) {
			case java.util.Calendar.SATURDAY:
				if(!satirday.equals("")) {
					satirday += ", ";
				}
				if(i < 10) {
					satirday += yyyy+"-"+month+"-"+"0"+i;
				}else {
					satirday += yyyy+"-"+month+"-"+""+i;
				}
			}
			
			switch(cal.get(cal.DAY_OF_WEEK)) {
			case java.util.Calendar.SUNDAY:
				if(!sunday.equals("")) {
					sunday += ", ";
				}
				if(i < 10) {
					sunday += yyyy+"-"+month+"-"+"0"+i;
				}else {
					sunday += yyyy+"-"+month+"-"+""+i;
				}
			}
			cal.clear();
		}
		dateList1.add(satirday);
		dateList2.add(sunday);
		
		resultMap.put("sat", dateList1);
		resultMap.put("sun", dateList2);
		
		return resultMap;
	}
}

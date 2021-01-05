package kr.co.kyobo.tower.group.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.bill.resource.service.ResourceService;
import kr.co.kyobo.common.service.CommonCodeService;
import kr.co.kyobo.common.service.SftpService;
import kr.co.kyobo.tower.group.service.GroupService;
import lombok.extern.slf4j.Slf4j;

/**
 * 성능 데이터 그룹 관리 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 7. 23. 최초작성 - 최지웅
 * </pre>
 *
 * @author 최지웅
 * @version 1.0 최초작성
 */
@Controller
@Slf4j
public class GroupController {

	@Autowired
	private GroupService groupService; 
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/group/index")
	public String indexPage(Model model) {
		return "thymeleaf/tower/group/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/group/list")
	public String listPage(Model model) {
		return "thymeleaf/tower/group/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/group/regist")
	public String registPage(Model model) {
		return "thymeleaf/tower/group/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/tower/group/update")
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
		
		return "thymeleaf/tower/group/update";
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!"-1".equals(params.get("length"))){
			int totalCnt = groupService.findCount(params);
			List<Map<String, Object>> dataList = groupService.findAll(params);
				
			resultMap.put("recordsTotal", totalCnt);
			resultMap.put("recordsFiltered", totalCnt);
			resultMap.put("data", dataList);
		}else {
			List<Map<String, Object>> dataList = groupService.findAll(params);
			resultMap.put("recordsTotal", dataList.size());
			resultMap.put("recordsFiltered", dataList.size());
			resultMap.put("data", dataList);
		}
		
//		int totalCnt = resourceService.findCount(params);
//		List<Map<String, Object>> dataList = resourceService.findAll(params);
//		
//		resultMap.put("recordsTotal", dataList.size());
//		resultMap.put("recordsFiltered", dataList.size());
//		resultMap.put("data", dataList);
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//		objectMapper.writeValue(new File("C:/dev/data/test.json"), resultMap);
		
		//JSch로 원격업로드
//		sftpService.init();
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return groupService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		
		int resultCnt = groupService.findCount(params);
		
		if(resultCnt > 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.exist.data", null, null));
		} else {
			resultCnt = groupService.insert(params);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
			}
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/updateData")
	public Map<String, Object> update(@RequestParam Map<String, Object> params
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("UPD_USER_ID", userDetails.getUsername());
		
		int resultCnt = groupService.update(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/saveSlaGroupId")
	public Map<String, Object> saveSlaGroupId(@RequestBody Map<String, Object> params
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String[] dataArrayGroupId = {};
		String[] dataArrayGroupNm = {};
		String[] dataArrayUseYn = {};
		
		if(!StringUtils.isEmpty(params.get("SLA_GROUP_ID"))) {
			dataArrayGroupId = params.get("SLA_GROUP_ID").toString().split(",", -1);
		}
		
		if(!StringUtils.isEmpty(params.get("SLA_GROUP_NM"))) {
			dataArrayGroupNm = params.get("SLA_GROUP_NM").toString().split(",", -1);
		}
		
		if(!StringUtils.isEmpty(params.get("USE_YN"))) {
			dataArrayUseYn = params.get("USE_YN").toString().split(",", -1);
		}
		
		for (int i = 0; i < dataArrayGroupId.length; i++) {
			int y = i + 1;
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("SLA_GROUP_ID", dataArrayGroupId[i]);
			groupMap.put("SLA_GROUP_NM", dataArrayGroupNm[i]);
			groupMap.put("USE_YN", dataArrayUseYn[y]);
			groupMap.put("ORDER_NO", i);
			groupMap.put("UPD_USER_ID", userDetails.getUsername());
			int resultCnt = groupService.saveSlaGroupId(groupMap);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
			}
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/tower/group/updateOrderNo")
	public Map<String, Object> updateOrderNo(@RequestBody Map<String, Object> params
			, Authentication authentication
//			, MultipartHttpServletRequest fileList
			) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String[] dataArrayGroupId = {};
		
		if(!StringUtils.isEmpty(params.get("SLA_GROUP_ID"))) {
			dataArrayGroupId = params.get("SLA_GROUP_ID").toString().split(",", -1);
		}
		
		for (int i = 0; i < dataArrayGroupId.length; i++) {
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("SLA_GROUP_ID", dataArrayGroupId[i]);
			groupMap.put("ORDER_NO", i);
			groupMap.put("UPD_USER_ID", userDetails.getUsername());
			int resultCnt = groupService.updateOrderNo(groupMap);
			
			if(resultCnt == 0) {
				resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
				resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
			}
		}
		
		return resultMap;
	}
}

package kr.co.kyobo.sr.srGroup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.sr.srGroup.service.SrGroupService;

/**
 * 서비스 요청 수신그룹 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 8. 31. 최초작성 - 최지웅
 * </pre>
 *
 * @author 최지웅
 * @version 1.0 최초작성
 */
@Controller
public class SrGroupController {

	@Autowired
	private SrGroupService srGroupService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/srGroup/index")
	public String indexPage(Model model) {
		return "thymeleaf/sr/srGroup/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/srGroup/list")
	public String listPage(Model model) {
		return "thymeleaf/sr/srGroup/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/srGroup/regist")
	public String registPage(@RequestParam Map<String, Object> params, Model model) {
		
		//String baselineId = params.get(arg0)
		params.put("TOWER_TYPE_CD", params.get("TOWER_TYPE_CD"));
		
		model.addAllAttributes(params);
		
		return "thymeleaf/sr/srGroup/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/srGroup/update")
	public String updatePage(@RequestParam Map<String, Object> params, Model model) {
		
		//String baselineId = params.get(arg0)
		params.put("TOWER_TYPE_CD", params.get("TOWER_TYPE_CD"));
		params.put("EMAIL", params.get("EMAIL"));
		params.put("NAME", params.get("NAME"));
		params.put("PHONE", params.get("PHONE"));
		
		model.addAllAttributes(params);
		
		return "thymeleaf/sr/srGroup/update";
	}
	
	@ResponseBody
	@RequestMapping("/sr/srGroup/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!"-1".equals(params.get("length"))){
			int totalCnt = srGroupService.findCount(params);
			List<Map<String, Object>> dataList = srGroupService.findAll(params);
				
			resultMap.put("recordsTotal", totalCnt);
			resultMap.put("recordsFiltered", totalCnt);
			resultMap.put("data", dataList);
		}else {
			List<Map<String, Object>> dataList = srGroupService.findAll(params);
			resultMap.put("recordsTotal", dataList.size());
			resultMap.put("recordsFiltered", dataList.size());
			resultMap.put("data", dataList);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/sr/srGroup/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		params.put("TOWER_TYPE_CD", params.get("TOWER_TYPE_CD"));
		params.put("EMAIL", params.get("EMAIL"));
		return srGroupService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/sr/srGroup/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		int resultCnt = srGroupService.insert(params);
			
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
		}
		
		return resultMap;
	}
	
	/**
	 * 타워그룹 목록 조회
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sr/srGroup/findTowerGroupList")
	public List<Map<String, Object>> findTowerGroupList(@RequestParam Map<String, Object> params) {
		return srGroupService.findTowerGroupList(params);
	}
	
	/**
	 * 타워그룹 목록 조회
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sr/srGroup/findTowerGroupNmList")
	public List<Map<String, Object>> findTowerGroupNmList(@RequestBody Map<String, Object> params) {
		return srGroupService.findTowerGroupNmList(params);
	}
}

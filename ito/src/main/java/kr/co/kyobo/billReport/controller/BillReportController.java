package kr.co.kyobo.billReport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.billReport.service.BillReportService;

/**
 * 과금현황 컨트롤러
 * 
 * <pre>
 * <b>이력:</b>
 * 		2020. 9. 9. 최초작성
 * </pre>
 *
 * @author 김태연
 * @version 1.0 최초작성
 */
@Controller
public class BillReportController {
	
	@Autowired
	private BillReportService billReportService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 월별 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/month/index")
	public String indexMonthPage(Model model) {
		return "thymeleaf/billReport/month/index";
	}
	
	/**
	 * 기간별 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/period/index")
	public String indexPeriodPage(Model model) {
		return "thymeleaf/billReport/period/index";
	}
	
	/**
	 * 타워별 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/tower/index")
	public String indexTowerPage(Model model) {
		return "thymeleaf/billReport/tower/index";
	}
	
	/**
	 * 월별 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/month/list")
	public String monthListPage(Model model) {
		return "thymeleaf/billReport/month/list";
	}
	
	/**
	 * 기간별 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/period/list")
	public String periodListPage(Model model) {
		return "thymeleaf/billReport/period/list";
	}
	
	/**
	 * 타워별 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/billReport/tower/list")
	public String towerListPage(Model model) {
		return "thymeleaf/billReport/tower/list";
	}
	
	/**
	 * 월 현황
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/billReport/month/dataList")
	public Map<String, Object> findMonthDataList(@RequestBody Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = billReportService.findMonthList(params);
		
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		
		return resultMap;
	}

	/**
	 * 기간별 현황
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/billReport/period/dataList")
	public Map<String, Object> findPeriodDataList(@RequestBody Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = billReportService.findPeriodList(params);
		
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
	/**
	 * 타워별 현황
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/billReport/tower/dataList")
	public Map<String, Object> findTowerDataList(@RequestBody Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> dataList = billReportService.findTowerList(params);
		
		resultMap.put("recordsFiltered", dataList.size());
		resultMap.put("data", dataList);
		
		return resultMap;
	}
}

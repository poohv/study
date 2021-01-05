package kr.co.kyobo.hst.detailHst.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kr.co.kyobo.bill.detail.service.DetailService;
import kr.co.kyobo.common.service.CommonCodeService;
import kr.co.kyobo.common.service.SftpService;
import kr.co.kyobo.hst.detailHst.service.DetailHstService;

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
@PropertySource(value = {"classpath:bill.properties"}, encoding = "UTF-8")
public class DetailHstController {
	
	@Autowired
	private DetailHstService detailHstService;
	
	@Autowired
	private SftpService sftpService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CommonCodeService commonCodeService;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/hst/detailHst/index")
	public String indexPage(Model model) {
		return "thymeleaf/hst/detailHst/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/hst/detailHst/list")
	public String listPage(Model model) {
		return "thymeleaf/hst/detailHst/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/hst/detailHst/regist")
	public String registPage(Model model) {
		return "thymeleaf/hst/detailHst/regist";
	}
	
	
	@ResponseBody
	@RequestMapping("/hst/detailHst/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int totalCnt = detailHstService.findCount(params);
		List<Map<String, Object>> dataList = detailHstService.findAll(params);
		
		resultMap.put("recordsTotal", totalCnt);
		resultMap.put("recordsFiltered", totalCnt);
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/hst/detailHst/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return detailHstService.findOne(params);
	}
	
}

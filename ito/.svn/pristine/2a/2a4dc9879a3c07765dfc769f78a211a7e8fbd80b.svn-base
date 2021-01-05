package kr.co.kyobo.sr.sr.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kyobo.config.CustomMailSender;
import kr.co.kyobo.sr.sr.service.SrService;

/**
 * 서비스 요청 컨트롤러
 * <pre>
 * <b>이력:</b>
 * 		2020. 08. 03. 최초작성 - 최지웅
 * </pre>
 *
 * @author 최지웅
 * @version 1.0 최초작성
 */
@Controller
public class SrController {
	
	@Autowired
	private SrService srService;
	
	@Autowired
	private CustomMailSender customMailSender;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment environment;
	
	/**
	 * 인덱스 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/sr/index")
	public String indexPage(Model model) {
		return "thymeleaf/sr/sr/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/sr/list")
	public String listPage(Model model) {
		return "thymeleaf/sr/sr/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/sr/regist")
	public String registPage(Model model) {
		return "thymeleaf/sr/sr/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/sr/sr/update")
	public String updatePage(@RequestParam Map<String, Object> params, Model model) {
		
		model.addAttribute("SR_ID", params.get("SR_ID"));
		model.addAttribute("SR_SEQ", params.get("SR_SEQ"));
		
		return "thymeleaf/sr/sr/update";
	}
	
	/**
	 * 수신자 목록 조회
	 * 
	 * @param params
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/sr/sr/findReceiveList")
	public List<Map<String, Object>> findReceiveList(@RequestBody Map<String, Object> params) throws MessagingException, IOException {
		List<Map<String, Object>> dataList = srService.findReceiveAll(params);
		
		return dataList;
	}
	
	/**
	 * 요청상세 목록 조회
	 * 
	 * @param params
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/sr/sr/findDetailList")
	public List<Map<String, Object>> findDetailList(@RequestBody Map<String, Object> params) throws MessagingException, IOException {
		List<Map<String, Object>> dataList = srService.findDetailAll(params);
		
		return dataList;
	}
	
	@ResponseBody
	@RequestMapping("/sr/sr/findDataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) throws MessagingException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!"-1".equals(params.get("length"))){
			int totalCnt = srService.findCount(params);
			List<Map<String, Object>> dataList = srService.findAll(params);
				
			resultMap.put("recordsTotal", totalCnt);
			resultMap.put("recordsFiltered", totalCnt);
			resultMap.put("data", dataList);
		}else {
			List<Map<String, Object>> dataList = srService.findAll(params);
			resultMap.put("recordsTotal", dataList.size());
			resultMap.put("recordsFiltered", dataList.size());
			resultMap.put("data", dataList);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/sr/sr/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return srService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/sr/sr/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
			, Authentication authentication) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		int resultCnt = srService.insert(params);
			
		if(resultCnt < 2) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
		} else {
			params.put("SR_LINK", environment.getProperty("domain.sr"));
			customMailSender.sendSRMail(params);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/sr/sr/updateData")
	public Map<String, Object> update(@RequestBody Map<String, Object> params
			, Authentication authentication
			) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.update.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("UPD_USER_ID", userDetails.getUsername());
		
		int resultCnt = srService.update(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.update.fail", null, null));
		}
		
		return resultMap;
	}
}

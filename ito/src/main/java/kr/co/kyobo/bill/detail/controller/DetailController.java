package kr.co.kyobo.bill.detail.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kr.co.kyobo.bill.detail.service.DetailService;
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
@Slf4j
@Controller
@PropertySource(value = {"classpath:bill.properties"}, encoding = "UTF-8")
public class DetailController {

	@Autowired
	private DetailService detailService;
	
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
	@RequestMapping("/bill/detail/index")
	public String indexPage(Model model) {
		return "thymeleaf/bill/detail/index";
	}
	
	/**
	 * 목록 화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/detail/list")
	public String listPage(Model model) {
		return "thymeleaf/bill/detail/list";
	}
	
	/**
	 * 등록화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/detail/regist")
	public String registPage(Model model) {
		return "thymeleaf/bill/detail/regist";
	}
	
	/**
	 * 수정화면 호출
	 * @param model
	 * @return
	 */
	@RequestMapping("/bill/detail/update")
	public String updatePage(@RequestParam Map<String, Object> params, Model model) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		conditions.put("DOMAINID", "CONTRACT_TYPE");
		model.addAttribute("contractTypeCdList", commonCodeService.findCodeList(conditions));
		model.addAllAttributes(params);
		
		return "thymeleaf/bill/detail/update";
	}
	
	@ResponseBody
	@RequestMapping("/bill/detail/dataList")
	public Map<String, Object> findDataList(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int totalCnt = detailService.findCount(params); //업로드 데이터 총 갯수
		List<Map<String, Object>> dataList = detailService.findAll(params);
		
		resultMap.put("recordsTotal", totalCnt);
		resultMap.put("recordsFiltered", totalCnt);
		resultMap.put("data", dataList);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/bill/detail/findData")
	public Map<String, Object> findData(@RequestBody Map<String, Object> params) {
		return detailService.findOne(params);
	}
	
	@ResponseBody
	@RequestMapping("/bill/detail/registData")
	public Map<String, Object> regist(@RequestBody Map<String, Object> params
			, Authentication authentication) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.regist.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		int resultCnt = detailService.insert(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.regist.fail", null, null));
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, true);
			
			List<Map<String, Object>> jsonDataList = detailService.findAllJson(params);
			
			//소문자 변경
			for (int i=0; i<jsonDataList.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) jsonDataList.get(i);
				Map<String, Object> tempMap = new HashMap<String, Object>();
				
				Set<String> set = map.keySet();
				Iterator<String> e = set.iterator();
				while (e.hasNext()) {
					String key = e.next();
					Object value = (Object) map.get(key);
					
					tempMap.put(key.toLowerCase(), (value == null) ? "" : value);
				}
				
				jsonDataList.set(i, tempMap);
			}
			
			String filePath = environment.getProperty("excel.bill.json.output.path");
			String fileName = environment.getProperty("excel.bill.json.output.filename");
			String elkPath = environment.getProperty("excel.bill.json.elk.path");
			String jsonFileName = filePath + fileName + "_" + params.get("BILL_MONTH").toString().replace("/", "") + ".json";
			
			File jsonFile = new File(jsonFileName);
			File jsonFileFolder = new File(jsonFileName.substring(0, jsonFileName.lastIndexOf("/")));
			if(!jsonFileFolder.exists()) jsonFileFolder.mkdirs();
			
			objectMapper.writeValue(jsonFile, jsonDataList);
			
			// 줄바꿈 및 개행 제거
			Path path = Paths.get(jsonFileName);
			Stream<String> lines = Files.lines(path);
			List<String> replaced = lines.map(line -> line.replaceAll("\\},\\{", "\\}\n\\{"))
					.collect(Collectors.toList());
			
			String jsonStr = replaced.toString();
			jsonStr = jsonStr.replaceAll("\\[", "");
			jsonStr = jsonStr.replaceAll("\\]", "");
			jsonStr = jsonStr.replaceAll("\t", "");
			jsonStr += "\n";	//개행문자 필수입력
			
			Files.write(path, jsonStr.getBytes());
			lines.close();
			
			if(200 == deleteElkData(params)) {
				//JSch로 원격업로드
				sftpService.init();
				sftpService.upload(elkPath, jsonFile);
				sftpService.disconnection();
				
				//파일 삭제
				jsonFile.delete();
			} else {
				//파일 삭제
				jsonFile.delete();
			}
		}
		
		return resultMap;
	}
	
	private int deleteElkData(Map<String, Object> params) throws Exception {
		String billMonth = params.get("BILL_MONTH").toString().replaceAll("/", "-");
		String query = "{\r\n" + 
				"	\"query\": {\r\n" + 
				"		\"bool\" : {\r\n" + 
				"			\"must\" : [\r\n" + 
				"				{\r\n" + 
				"					\"query_string\":{\r\n" + 
				"						\"analyze_wildcard\": true,\r\n" + 
				"						\"query\": \"*\"\r\n" + 
				"					}\r\n" + 
				"				},\r\n" + 
				"				{\r\n" + 
				"					\"range\": {\r\n" + 
				"						\"bill_month\":{\r\n" + 
				"							\"gte\" : \"" + billMonth + "-01T00:00:00.000Z\",\r\n" + 
				"							\"lte\" : \"" + billMonth + "-01T00:00:00.000Z\"\r\n" + 
				"						}\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"			],\r\n" + 
				"			\"must_not\" : []\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
		
		String elkUrl = environment.getProperty("spring.data.elasticsearch.http-url");
		
		URL url = new URL("http://" + elkUrl + "/kbl_billng_report*/_delete_by_query");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-ndjson");
		con.setDoOutput(true);
		
		DataOutputStream os = new DataOutputStream(con.getOutputStream());
		os.writeBytes(query);
		os.flush();
		os.close();
		
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) !=  null) {
			response.append(inputLine);
		}
		
		in.close();
		
		//log.info("HTTP 응답코드 : " + responseCode);
		//log.info("HTTP Body : " + response.toString());
		
		return responseCode;
	}
}

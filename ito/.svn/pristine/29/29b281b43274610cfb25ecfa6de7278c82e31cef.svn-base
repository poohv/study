package kr.co.kyobo.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.kyobo.common.service.AttachService;

/**
 * 첨부파일 관리 컨트롤러
 * 
 * <pre>
 * <b>이력:</b>
 * 		2020. 9. 1. 최초작성 - 김태연
 * </pre>
 *
 * @author 김태연
 * @version 1.0 최초작성
 */
@Controller
@RequestMapping("/attach")
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private Environment environment;
	
	/**
	 * 파일 업로드
	 * 
	 * @param params
	 * @param request
	 * @param authentication
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public Map<String, Object> fileUpload(@RequestParam Map<String, Object> params
			, MultipartHttpServletRequest request
			, Authentication authentication) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", messageSource.getMessage("msg.result.success", null, null));
		resultMap.put("resultMsg", messageSource.getMessage("msg.file.upload.success", null, null));
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		params.put("REG_USER_ID", userDetails.getUsername());
		
		List<MultipartFile> fileList = request.getFiles("ATTACH_FILE");
		String uploadPath = environment.getProperty("upload.path") + "sr/";
		File uploadFolder = new File(uploadPath);	
		
		if(!uploadFolder.exists()) uploadFolder.mkdirs();
		
		List<Map<String, Object>> fileDetailList = new ArrayList<Map<String, Object>>();
		
		for (int i=0; i<fileList.size(); i++) {
			MultipartFile multipartFile = fileList.get(i);
			long fileName = System.nanoTime();
			String uploadURL = uploadPath + fileName;
			
			// 파일 업로드
			File file = new File(uploadURL);
			multipartFile.transferTo(file);
			
			Map<String, Object> attachDetailMap = new HashMap<String, Object>();
			attachDetailMap.put("ORDER_NO", i);
			attachDetailMap.put("FILE_NAME", fileName);
			attachDetailMap.put("ORG_FILE_NAME", multipartFile.getOriginalFilename());
			attachDetailMap.put("EXTENSION", FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
			
			attachDetailMap.put("PATH", uploadURL);
			attachDetailMap.put("SIZE", multipartFile.getSize());
			
			fileDetailList.add(attachDetailMap);
		}
		
		params.put("detailList", fileDetailList);
		
		// 첨부파일 정보 등록
		int resultCnt = attachService.insert(params);
		
		if(resultCnt == 0) {
			resultMap.put("result", messageSource.getMessage("msg.result.fail", null, null));
			resultMap.put("resultMsg", messageSource.getMessage("msg.file.upload.fail", null, null));
		} else {
			resultMap.put("ATTACH_ID", params.get("ATTACH_ID"));
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/dataList")
	public List<Map<String, Object>> findDataList(@RequestBody Map<String, Object> params) {
		List<Map<String, Object>> dataList = attachService.findAll(params);
		
		return dataList;
	}
	
	/**
	 * 파일 다운로드
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/download")
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params) throws IOException {
		Map<String, Object> fileMap = attachService.findOne(params);
		HttpHeaders header = new HttpHeaders();
		Resource rs = null;
		
		if(fileMap != null) {
			String filePath = fileMap.get("PATH").toString();
			String fileName = fileMap.get("ORG_FILE_NAME").toString();
			
			Path path = Paths.get(filePath);
			String mimeType = Files.probeContentType(path);
			
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			}
			
			header.setContentType(MediaType.parseMediaType(mimeType));
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
			header.setCacheControl("no-cache");
			
			rs = new InputStreamResource(Files.newInputStream(path)); 
		}
		
		return new ResponseEntity<Resource>(rs, header, HttpStatus.OK);
	}
}

package kr.co.kyobo.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 예외 처리 핸들러
 * <pre>
 * <b>이력:</b>
 * 		2020. 7. 22. 최초작성 - 김태연
 * </pre>
 *
 * @author 김태연
 * @version 1.0 최초작성
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public GlobalExceptionHandler() {};
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MultipartProperties multipartProperties;
	
	/**
	 * 파일 업로드 사이즈 예외 처리
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {MaxUploadSizeExceededException.class})
	@ResponseBody
	public Map<String, Object> fileSizeLimitExceededException(MaxUploadSizeExceededException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", messageSource.getMessage("msg.file.upload.size.fail"
				, new Object[] {multipartProperties.getMaxFileSize().toMegabytes()}
				, null));
		
		return errorMap;
	}
	
	@ExceptionHandler(value = {EncryptedDocumentException.class})
	@ResponseBody
	public Map<String, Object> encryptedDocumentException(EncryptedDocumentException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", messageSource.getMessage("msg.file.upload.password.fail"
				, null
				, null));
		return errorMap;
	}
	
	@ExceptionHandler(value = {IOException.class})
	@ResponseBody
	public Map<String, Object> inOutException(IOException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", e.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(value = {JsonGenerationException.class})
	@ResponseBody
	public Map<String, Object> jsonGenerationException(JsonGenerationException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", e.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(value = {JsonMappingException.class})
	@ResponseBody
	public Map<String, Object> jsonMappingException(JsonMappingException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", e.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(value = {MessagingException.class})
	@ResponseBody
	public Map<String, Object> messagingException(MessagingException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		
		errorMap.put("result", "F");
		errorMap.put("resultMsg", e.getMessage());
		return errorMap;
	}
}

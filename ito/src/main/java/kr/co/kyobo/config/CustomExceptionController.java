package kr.co.kyobo.config;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
public class CustomExceptionController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@RequestMapping("/error")
	public String hadlerError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		
		model.addAttribute("code", status.toString());
		model.addAttribute("msg", httpStatus.getReasonPhrase());
		model.addAttribute("timestamp", new Date());
		
		return "thymeleaf/error/error";
	}
}

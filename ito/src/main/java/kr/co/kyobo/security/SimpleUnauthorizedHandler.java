package kr.co.kyobo.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import kr.co.kyobo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        //log.debug(" commence !! ");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        JsonUtils.write(response.getWriter(), new HashMap<String, Object>() {{
            put("status", HttpStatus.UNAUTHORIZED.value());
            put("message", Optional.ofNullable(e.getMessage()).orElse("Unauthorized"));
        }});
    }
}

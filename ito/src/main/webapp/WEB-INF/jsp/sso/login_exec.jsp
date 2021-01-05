<%-- <%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="./config.jsp" %>
<%
	// 1. SSO ID 조회
	String sso_id = getSsoId(request);
	//sso_id = null;
	System.out.println("*================== [login_exec.jsp]  sso_id = "+sso_id);
	if (sso_id == null || sso_id.equals("")) {
		goLoginPage(response);
		return;
	} else {

		// 2. SSO 인증토큰의 유효성 검증 (정상 : 0)
		String retCode = getEamSessionCheckAndAgentVaild(request,response);
		System.out.println("*================== [retCode]  retCode = " + retCode);
	
		if(!retCode.equals("0")){
			goErrorPage(response, Integer.parseInt(retCode));
			return;
		}

		// 3. SSO ID를 업무시스템에서 사용할 세션에 생성
		// 그 외 세션 생성 등 업무시스템에서 필요한 사항 처리
		String SSO_ID = (String)session.getAttribute("SSO_ID");
		if(SSO_ID == null || SSO_ID.equals("")) {
			session.setAttribute("SSO_ID", sso_id);
		}
		// out.println("SSO 인증 성공!!");

		// 4. 업무시스템 페이지 이동 (업무시스템 메인페이지로 이동 - 환경에 맞게 적절히 수정함)
		response.sendRedirect("/ito/sso/login_exec");
		// out.println("인증성공");
	}
%>

 --%>
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
		response.sendRedirect("/ito/sso/login");
		// out.println("인증성공");
	}
%>

<!DOCTYPE html>
<html layout:decorate="~{thymeleaf/layout/layout_blank}">
<head>
    <title layout:title-pattern="$LAYOUT_TITLE : $CONTENT_TITLE">로그인</title>
</head>
<body>
	<div layout:fragment="content" class="login-page">
		<div class="login-box">
			<div class="login-logo">
				<a href="#">로그인</a>
			</div>
  			<!-- /.login-logo -->
			<div class="card">
				<div class="card-body login-card-body">
					<h5 class="login-box-msg">ITO 서비스 포탈</h5>
					<form id="login-form" th:action="@{/sso/login}" method="post">
						<!-- <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" /> -->
						<div class="input-group mb-3">
							<input type="text" name="username" class="form-control" placeholder="아이디" required="required">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<div class="input-group mb-3">
							<input type="password" name="password" class="form-control" placeholder="패스워드" required="required">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-8">
							</div>
							<!-- /.col -->
							<div class="col-4">
								<button type="submit" class="btn btn-primary btn-block">로그인</button>
							</div>
							<!-- /.col -->
						</div>
						
						<div class="row mt-3">
							<div class="col-sm-12">
								<div class="alert alert-warning" data-th-if="${session.SPRING_SECURITY_LAST_EXCEPTION != null}">
									<i class="icon fas fa-exclamation-triangle"></i> [(#{msg.fail.login})]
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /.login-box -->
		</div>
	</div>
	<th:block layout:fragment="script">
		<script>
			$(function() {
				var form = $("#login-form");
				form.validate({
					errorPlacement: function(error, element) {
						if ( element.hasClass( "select2" )) {
							$(element).next().find('span span').addClass('is-invalid');
						}
					}
				});
				
				$('#remember').on('click', function(e) {
					if($(this).is(':checked')) {
						// 아이디 쿠키 저장
						$.cookie('ITO_LOGIN_ID', $(form).find('[name="username"]').val(), {expires: 7, path: '/ito'});
					} else {
						// 아이디 쿠키 삭제
						$.removeCookie('ITO_LOGIN_ID', {path: '/ito'});
					}
				});
			});
		</script>
	</th:block>
</body>
</html>

 --%>
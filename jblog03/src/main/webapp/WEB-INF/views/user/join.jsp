<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	btn = $('#btn-check');
	btn.click(function(){
		var id = $("#blog-id").val();
		if(id == ""){
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e){
				console.error(status, e);
			},
			success: function(response){
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if(response.data){
					alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#btn-check").hide();
				$("#img-check").show();
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			<p style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="name" />
			</p>
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input type="button" id="btn-check" value="중복체크">
			
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="id" />
			</p>
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<p style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="password" />
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>

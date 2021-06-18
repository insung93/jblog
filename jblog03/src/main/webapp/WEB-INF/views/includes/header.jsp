<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<h1 class="logo" style="background-image:url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
	<ul class="menu">
	<li><a href="${pageContext.request.contextPath}">홈</a></li>
	<c:choose>
		<c:when test="${empty authUser.id }">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id}">My블로그</a></li>
		</c:otherwise>
	</c:choose>
	</ul>
</div>
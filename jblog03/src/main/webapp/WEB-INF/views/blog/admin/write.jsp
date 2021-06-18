<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header_admin.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
				<c:choose>
					<c:when test="${empty categoryList }">
						<p align="center">
							카테고리를 추가 해주세요<br><br>
							<a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리 추가하기</a>
						</p>
					</c:when>
					<c:otherwise>
						<form action="${pageContext.request.contextPath}/${authUser.id}/admin/write" method="post">
					      	<table class="admin-cat-write">
					      		<tr>
					      			<td class="t">제목</td>
					      			<td>
					      				<input type="text" size="60" name="title">
						      			<select name="categoryNo">
						      				<c:forEach var="category" items="${categoryList }">
							      				<option value="${category.no }">${category.name }</option>
						      				</c:forEach>
						      			</select>
						      		</td>
					      		</tr>
					      		<tr>
					      			<td class="t">내용</td>
					      			<td><textarea name="contents"></textarea></td>
					      		</tr>
					      		<tr>
					      			<td>&nbsp;</td>
					      			<td class="s"><input type="submit" value="포스트하기"></td>
					      		</tr>
					      	</table>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_admin.jsp" />
	</div>
</body>
</html>
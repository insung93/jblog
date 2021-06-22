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
					<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
			      	<c:if test="${empty categoryList }">
		      		<tr>
		      			<td colspan="5">
				      		<p align="center">
				      			<br><br>↓↓↓ 카테고리를 <strong>추가</strong> 해주세요. ↓↓↓<br><br><br>
				      		</p>
			      		</td>
		      		</tr>
			      	</c:if>
		      		<c:forEach var="category" items="${categoryList }">
					<tr>
						<td>${category.no }</td>
						<td>${category.name }</td>
						<td>${category.count }</td>
						<td>${category.desc }</td>
						<td><a href="${pageContext.request.contextPath}/${authUser.id}/admin/deletecategory/?no=${category.no }"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"/></a></td>
					</tr>
					</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath}/${authUser.id}/admin/addcategory" method="post">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_admin.jsp" />
	</div>
</body>
</html>
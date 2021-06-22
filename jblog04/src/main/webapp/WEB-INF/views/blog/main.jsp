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
			<div id="content">
			<c:choose>
				<c:when test="${empty postList }">
					<br><br><br><br><br><br><br><br>
					<p style="text-align:center;">게시글이 없습니다.</p>
				</c:when>
				<c:otherwise>
					
						<div class="blog-content">
							<h4>${postVo.title }</h4>
							<p>${postVo.contents }<p>
						</div>
						<ul class="blog-list">
						<c:forEach var="post" items="${postList }">
							<li><a href="${pageContext.request.contextPath}/${blogvo.id}/${categoryNo}/${post.no}">${post.title }</a> <span>${post.regDate }</span>
						</c:forEach>
						</ul>
					
				
				</c:otherwise>
			</c:choose>
			</div>
		</div>



		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:choose>
				<c:when test="${empty categoryList }">
					<p style="text-align:center;">카테고리가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="category" items="${categoryList }">
						<li><a href="${pageContext.request.contextPath}/${blogvo.id}/${category.no}">${category.name}　</a></li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
				
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_admin.jsp" />
	</div>
</body>
</html>
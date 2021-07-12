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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script>
var messageBox = function(title, message, callback) {
	$("#dialog-message p").text(message);
	$("#dialog-message").attr("title", title).dialog({
		modal: true,
		buttons: {
			"확인": function() {
				$(this).dialog( "close" );
			}
		},
		close: callback
	});
}

var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
});
var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});

var fetchList = function(){
	$.ajax({
		url: '${pageContext.request.contextPath }/'+${authUser.id}+'/admin/list',
		type: 'get',
		dataType: 'json',
		success: function(response){
			//rendering
			let html = listTemplate.render(response);
			$(".admin-cat tr:last").after(html);
		}
	});
}



//입력폼 submit 이벤트
$(function(){
	fetchList();
	
	$('#add-form').submit(function(event){
		event.preventDefault();
		var vo = {};
		vo.name = $("#input-name").val();
		if(vo.name == ''){
			messageBox("새로운 카테고리 추가하기", "카테고리명은 필수입니다.", function(){
				$("#input-name").focus();
			});
			return;
		}
		vo.desc = $("#input-desc").val();
		if(vo.desc == ''){
			messageBox("새로운 카테고리 추가하기", "설명은 필수 항목 입니다.", function(){
				$("#input-desc").focus();
			});
			return;
		}
		$.ajax({
			url: '${pageContext.request.contextPath }/'+${authUser.id}+'/admin/addcategory',
			async: true,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response){
				console.log("response");
				if(response.result != "success"){
					//console.error(response.message);
					return;
				}
				// rendering
				let html = listItemTemplate.render(response.data);
				// 첫번째 줄에 넣기
				//$(".admin-cat tr:first").after(html);
				$(".admin-cat tr:last").after(html);
				// form reset
				$("#add-form")[0].reset();
				
				console.log($(".admin-cat tr").length);
				
				for(i = 2 ; i < $(".admin-cat tr").length+1 ; i++) {
					$(".admin-cat tr:nth-child("+i+") td:nth-child(1)").text(i-1);
				}
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});

	});
	
	$(document).on("click", ".admin-cat td a", function() {
		event.preventDefault();
		var no = $(this).data("no");
		console.log(no);
		deleteCategory(no);
	});
	
	deleteCategory = function(no) {
		$.ajax({
			url : "${pageContext.request.contextPath }/${authUser.id }/admin/delete/"+no,
			dataType : "json", // 받을 때 format
			type : "delete", // 요청 method
			success : function(response) {
				$(".admin-cat tr[data-no="+ response.data+ "]").remove();
				
				var len = $(".admin-cat tr").length;
				for(var i = 2 ; i <= $(".admin-cat tr").length + 1 ; i++){
					$(".admin-cat tr:nth-child("+i+") td:first").text(i-1);
				}
			}
		});
	};
	
	
	
});



</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header_admin.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id}/spa">카테고리</a></li>
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
		      		<!-- 
			      	<c:if test="${empty categoryList }">
		      		<tr>
		      			<td colspan="5">
				      		<p align="center">
				      			<br><br>↓↓↓ 카테고리를 <strong>추가</strong> 해주세요. ↓↓↓<br><br><br>
				      		</p>
			      		</td>
		      		</tr>
			      	</c:if>
			      	
			      	 -->
			      	 <!--
		      		<c:forEach var="category" items="${categoryList }">
					<tr>
						<td>${category.no }</td>
						<td>${category.name }</td>
						<td>${category.count }</td>
						<td>${category.desc }</td>
						<td><a href="${pageContext.request.contextPath}/${authUser.id}/admin/deletecategory/?no=${category.no }"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"/></a></td>
					</tr>
					</c:forEach>
					-->
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="add-form" action="" method="post">
      			<!-- <form action="${pageContext.request.contextPath}/${authUser.id}/admin/addcategory" method="post"> -->

		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="input-name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="input-desc" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
		      	</form>
      			<div id="dialog-message" title="" style="display:none">
					<p></p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer_admin.jsp" />
	</div>
</body>
</html>
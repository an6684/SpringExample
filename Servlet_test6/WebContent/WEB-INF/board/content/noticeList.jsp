<%@page import="com.sun.jdi.request.InvalidRequestStateException"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.web.VO.content.contentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<c:set var="root" value="${pageContext.request.contextPath}" />
	<style>
		body{margin:0;padding:0;font-family:tahoma,nanumgothic;font-size:14px;line-height:180%;}
		.wrap{width:1000px;margin:0 auto;}
		table{width:100%;border-top:1px solid #ccc;border-collapse:0;border-spacing:0;}
		table td,table th{padding:0.5rem 1.0rem;border-bottom:1px solid #ccc;}
		table th{background:#f1f1f1;}
		table td.td-tit{width:60%;}
		
		.top{height:1.5rem;margin:1.0rem 0.5rem;}
		.a-c{text-align:center;}
		.a-r{text-align:right;}
		.ma-t-1{margin-top:1.0rem;}
		.color-crim{color:crimson;}
		.f-l{float:left;}
		.f-r{float:right;}
		a{text-decoration:none;color:#666;}
		a:link{text-decoration:none;}
		a:visited{color:#999;}
		a:hover{color:tomato;}
	</style>
</head>
<body>
	<div class="wrap">
		<div class="top">
			<div class="f-l">${name} 님 반갑습니다.</div>
			<input type="button" value="Log Out" onclick="location.href='/user/logout'" class="f-r" />
		</div>
		
		<form action="" method="get">
			<div>
				<select name="boardid">
					<option ${(param.boardid=="1")?"selected":""} value="1">공지사항</option>
					<option ${(param.boardid=="2")?"selected":""} value="2">자유게시판</option>
				</select> 
				<span><input type="submit" value="변경"></span>
			</div>
		</form>
	
		<div>
			<a href="/board/content/list?boardid=1">공지사항</a>
			<span> | </span>
			<a href="/board/content/list?boardid=2">자유게시판</a>
		</div>
	
	
		<!--content list  -->
		<table>
			<tr>
				<th>ID</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>내용</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="li" items="${list}">
				<tr>
					<td class="a-c">${li.id}</td>
					<td><a href="detail?id=${li.id}">${li.title}</a></td>
					<td class="a-c">${li.writeid}</td>
					<td>${li.content}</td>
					<td class="a-c"><fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${li.regdate}" /></td>
					<td class="a-c">${li.hit}</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 글쓰기 버튼 -->
		<div>
			<input type="button" onclick="location.href='regedit'" value="글쓰기" />
		</div>
		
		<h2>${count}건이 조회되었습니다.</h2>
		
		<!-- 변수선언 -->
		<c:set var="page" value="${empty param.p?1:param.p}"></c:set>
		<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
		<c:set var="lastNum"  value="${fn:substringBefore(Math.ceil(4.9),'.')}"></c:set>
			
		<!-- 현재 페이지 -->
		<div>
			<h3>현재 페이지</h3>
			<div>
				<span> ${page} </span> / ${lastNum} pages
			</div>
		</div>
	
		<!-- 페이징처리 시작 -->
		<!-- 이전 페이지 -->
		<c:if test="${startNum > 1 }">
			<a href="?p=${startNum-1}&f=${param.f}&q=${param.q}">Prev</a>
		</c:if>
		<c:if test="${startNum <= 1 }">
			<a href="#" onclick="alert('첫 페이지입니다.');">Prev</a>
		</c:if>
	
		<!-- 숫자 페이지 -->
		<ul>
			<c:forEach var="i" begin="0" end="4">
				<c:if test="${param.p==(startNum+i)}">
					<c:set var="style" value="font-weight:bold; color:red;" />
				</c:if>
				<c:if test="${param.p!=(startNum+i)}">
					<c:set var="style" value="" />
				</c:if>
				<c:if test="${(startNum+i) <=lastNum }">
					<li><a style="${style} "
						href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	
		<!-- 다음 페이지 -->
		<c:if test="${startNum+5 <= lastNum }">
			<a href="?p=${startNum+5}&f=${param.f}&q=${param.q}">Next</a>
		</c:if>
		<c:if test="${startNum+5 >lastNum }">
			<a href="#" onclick="alert('마지막 페이지입니다.');">Next</a>
		</c:if>
		
		<!-- search -->
		<form action ="" method="get">
			<div>
				<select name="cds">
					<option ${(param.cds=="title")?"selected":""} value="title">제목</option>
					<option ${(param.cds=="writeid")?"selected":""} value="writedid">글쓴이</option>
				</select>
				<input type="text" name="sw" /> 
					<span>
						<input type="submit" value="검색">
					</span>
			</div>
		</form>
	</div>
</body>
</html>
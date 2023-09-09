<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>글쓴이</th>
			<td>${detail.writeid }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${detail.title}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${detail.hit }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${detail.regdate}</td>
		</tr>
		<tr>
			<th colspan=2>내용</th>
		</tr>
		<tr>
			<td colspan=2>${detail.content}</td>
		</tr>
		<tr>
			<th colspan=2>첨부파일</th>
		</tr>
		<tr>
			<td colspan=2><a download href="/upload/${detail.filepath}">${fn:toUpperCase(detail.filepath)}</a></td>
		</tr>
	</table>

	<c:choose>
		<c:when test="${right eq '1'}">
			<input type="button" value="수정" onclick="location.href='/board/content/modify?id=${detail.id}'" />
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
	
	<!-- 댓글 리스트 -->
	<table>
			<tr>
				<th>내용</th>
				<th>글쓴이</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="cl" items="${cmList}">
				<tr>
					<td>${cl.content}</td>
					<td class="a-c">${cl.writeId}</td>
					<td class="a-c"><fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${cl.regdate}" /></td>
				</tr>
			</c:forEach>
		</table>
	
	<!-- 댓글 입력 -->
	<form action="" name="insert_comment" method="post">
		<table border="1">
			<tr>
				<td>
					<input type="text" name="comment" placeholder="댓글 입력"/>
					<input type="hidden" name="contentid" value="${detail.id }"/>
				</td>
				<td>
					<input type="submit" value="입력"/>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
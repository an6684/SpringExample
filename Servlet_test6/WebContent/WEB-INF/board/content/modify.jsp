<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${name} 님 어서오세요.</h1>
	
	<form action="modify" method="post" >
	<table border=1>
		<tbody>
			<tr>
				<th>글쓴이</th>
				<td>${detail.writeid }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${detail.title}"></td>
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
				<th>글내용</th>
				<td><!-- content <text-->
					<textarea name="content">${detail.content}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name ="id" value="${detail.id}">
	<input type="submit" value="저장"/>
	</form>
</body>
</html>
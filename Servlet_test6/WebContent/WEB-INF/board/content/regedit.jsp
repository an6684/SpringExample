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
	
	<form action="regedit" method="post" enctype="multipart/form-data" >
	<table border=1>
		<tbody>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><!-- content <text-->
					<textarea name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="file" name="uploadFile">
				</td>
			</tr>
		</tbody>
	</table>
	<input type="submit" value="저장"/>
	</form>
</body>
</html>
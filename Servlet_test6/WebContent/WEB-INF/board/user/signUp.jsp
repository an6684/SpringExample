<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<script type="text/javascript" src="${root}/js/user/signUp.js"></script>
</head>
<body>
<div>위치는 : ${root}</div>

	<section>
		<form id="joinform" name="joinform" action="" method="post" onsubmit="return createFrom(this)">

			<h4 style="text-align: center;">회원가입(*필수입력사항입니다.)</h4>
			
			<div class="menu" style="border-bottom-width: 0px;">
				<div id="id">아이디</div>
				<span>* <input type="text" class="checkInfo" name="id"
					size="12" />
					<button type="button" onclick="idCheck(joinform, '${root}')" >아이디 중복</button>
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">비밀번호</div>
				<span>* <input type="password" class="checkInfo"
					name="password" size="12" />
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">비밀번호확인</div>
				<span>* <input type="password" class="checkInfo"
					name="passwordCheck" size="12" />
				</span>
			</div>

			<div class="menu " style="border-bottom-width: 0px;">
				<div id="id">이름</div>
				<span>* <input type="text" class="checkInfo" name="name" size="12" />
				</span>
			</div>


			<div class="menu" style="border-bottom-width: 0px;">
				<div id="id" style="margin-left: 10px,">이메일</div>
				<span> <input type="email" name="email" size="25" />
				</span>
			</div>
			<div class="menu" style="text-align: center;">
				<span> 
					<input type="submit" value="가입" /> 
					<input type="reset" value="취소" />
				</span>
			</div>
		</form>
	</section>
</body>
</html>
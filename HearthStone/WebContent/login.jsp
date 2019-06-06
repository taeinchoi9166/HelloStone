<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./TopBar.jsp"></jsp:include>
<section>

<div class="card">
	<div class="card-body">
		<h3>로그인</h3><br>
		<form action="/HearthStone/login" method="post" id="form">
			<h6>아이디 </h6> <input type="text" class="form-control" name="user_id"><br>
			<h6>비밀번호 </h6><input type="password" class="form-control" name="user_pw"><br>
			<a href="#" onclick="document.getElementById('form').submit()">로그인</a><br>
			<span class="mini_text">로그인 실패시 메인으로 강제이동 됩니다.</span>
		</form>
	</div>
</div>
</section>
<jsp:include page="./footer.jsp"></jsp:include>
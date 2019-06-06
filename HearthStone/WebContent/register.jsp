<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./TopBar.jsp"></jsp:include>
<section>

	<div class="card">
		<div class="card-body">
			<h3>회원가입</h3><br>
			<form action="/HearthStone/register" method="post" id="form">
				<h6>아이디 </h6> <input type="text" class="form-control" name="user_id"><br>
				<h6>비밀번호 </h6><input type="password" class="form-control" name="user_pw"><br>
				<a href="#" onclick="document.getElementById('form').submit()">가입</a>
			</form>
		</div>
	</div>
</section>
<jsp:include page="./footer.jsp"></jsp:include>
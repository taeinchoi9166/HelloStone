<%@page import="domain.LoginEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<link href="./css/style.css" rel="stylesheet">
</head>
<body>
	<%
		LoginEntity ob = (LoginEntity)session.getAttribute("logined_user");
	%>
	<div class="container">
		<header>
			<h2 id="header_title">HelloStone Decks - <p>웹크롤링 하스스톤 사이트 </p></h2>
			<nav>
				<ul id="header_menu_wrap">
					<li class="menu_list"><a href="/HearthStone/metas"><button type="button" class="btn btn-primary">티어덱 보기</button></a></li>
					<li class="menu_list"><a href="/HearthStone/customs"><button type="button" class="btn btn-primary">사용자 덱 보기</button></a></li>
					<%
						if(ob!=null){
					%>
						<li class="menu_list"><a href="/HearthStone/mydeck"><button type="button" class="btn btn-primary">저장한 덱 보기</button></a></li>
						<li class="menu_list"><a href="/HearthStone/logout"><button type="button" class="btn btn-warning">로그아웃 <%=ob.getId()%></button></a></li>
					<%		
						}else{
					%>
						<li class="menu_list"><a href="/HearthStone/login.jsp"><button type="button" class="btn btn-success">로그인 </button></a></li>
						<li class="menu_list"><a href="/HearthStone/register.jsp"><button type="button" class="btn btn-info">회원가입 </button></a></li>
					<%
						}
					%>
				</ul>	
			</nav>
		</header>
<%@page import="java.util.List"%>
<%@page import="domain.MyDeckItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./TopBar.jsp"></jsp:include>
<%
	List<MyDeckItem> list = (List<MyDeckItem>)request.getAttribute("list");
	request.setAttribute("list",list);
%>
<section>
<table class="table table-striped">
	<thead>
		<tr>
			<td>직업</td><td>게임 모드</td><td>덱 이름</td><td>저장 날짜</td><td>링크</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td><img src="./img/${item.deck_class}.png" class="class_img"></td>
				<td>${item.deck_type}</td>
				<td>${item.deck_name}</td>
				<td>${item.saved_date.getYear()+1900}-${item.saved_date.getMonth()+1}-${item.saved_date.getDate()}</td>
				<td><a href="${item.deck_link}">보러가기</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</section>
<jsp:include page="./footer.jsp"></jsp:include>
<%@page import="java.util.Date"%>
<%@page import="logicUtil.LogicUtil"%>
<%@page import="domain.LoginEntity"%>
<%@page import="java.util.List"%>
<%@page import="domain.CustomDeckItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./TopBar.jsp"></jsp:include>
<section>
<h3>최신 커스텀 덱 by inven</h3>
<canvas id="custom_chart"></canvas>
<%
	LogicUtil util = new LogicUtil();
	List<CustomDeckItem> items = (List<CustomDeckItem>)request.getAttribute("custom_list");
	LoginEntity ob = (LoginEntity)session.getAttribute("logined_user");
	int n = 1;
	request.setAttribute("items",items);
	request.setAttribute("n",n);
%>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<td>덱 이름</td><td>직업</td><td>게임 모드</td><td>가루 비용</td><td>인벤 링크</td>
			<td>관심 덱 추가</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${items}" var="t">
		<tr>
			<td>${t.deckName}</td>
			<td><img src="./img/${t.deckClass}.png" class="class_img"></td>
			<td>${t.deckType}</td>
			<td>${t.deckNeedDust}가루</td>
			<td><a href="${t.deckLink}">보러가기</a></td>
			<td>
				<form action="/HearthStone/getdeck" method="post" id="form${n}">
					<input type="hidden" name="deck_name" value="${t.deckName}">
					<input type="hidden" name="deck_class" value="${t.deckClass.trim()}">
					<input type="hidden" name="deck_type" value="${t.deckType}">
					<input type="hidden" name="deck_link" value="${t.deckLink}">
					<%if(ob!=null){%>
						<a href="#" onclick="document.getElementById('form${n}').submit()">저장</a>
					<%}else{ %>로그인 하세요.<%}%>
				</form>
			</td>
			<c:set var="n" value="${n+1}"/>
		</tr>	
	</c:forEach>
	</tbody>
</table>
</section>
<script>
$(function(){
	var ctx = document.getElementById("custom_chart").getContext("2d");
	var metaChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	    	 labels: ["정규", "야생"],
	         datasets: [{
	             label: "사용자 덱의 게임 모드 별 비교 ",
	             backgroundColor: ['#8c653c','#63993a'],
	             borderColor: 'white',
	             data: [<%=util.getModeCount("정규") %>,<%=util.getModeCount("야생")%>],
	         }]
	
	    },
	    options: {
	    	title:{
	    		display:true,
	    		text:'<%=new Date().getYear()+1900+"년 "+(new Date().getMonth()+1)+"월 "+ (new Date().getDate())+"일 " %> 사용자 덱의 게임모드 별 덱 숫자 ',
	    		fontSize:25
	    	}
	    
	    }
    });
});
</script>
<jsp:include page="./footer.jsp"></jsp:include>
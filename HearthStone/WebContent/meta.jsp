<%@page import="domain.LoginEntity"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="domain.ClassCount"%>
<%@page import="logicUtil.LogicUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="domain.MetaItem"%>
<%@page import="java.util.List"%>
<%@page import="domain.CustomDeckItem"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./TopBar.jsp"></jsp:include>
<section>
<h3>최신 메타 덱 by 하스스터디</h3>
<canvas id="meta_chart"></canvas>
<%
	LogicUtil util = new LogicUtil();
	List<MetaItem> items = (List<MetaItem>)request.getAttribute("items");
	
	LoginEntity ob = (LoginEntity)session.getAttribute("logined_user");
	int line_tier = 1;
	request.setAttribute("items", items);
	request.setAttribute("line_tier",line_tier);
%>
	<hr>tier <%=line_tier%><hr>
	<table class="table">
		<tr>
			<th>덱 순위 </th><th>전 순위</th><th>직업 </th><th>게임 모드 </th><th>덱 이름 </th><th>덱 링크 </th><th>덱 저장하기 </th>
		</tr>
	<c:forEach items="${items}" var="t">
		<c:if test="${t.tier!=line_tier}">
			<c:set var="line_tier" value="${line_tier+1}"/>
			</table>
			<hr>tier ${line_tier}<hr>
			<table class="table">
		</c:if>
		<tr>
			<td>${t.deck_no}위</td>
			<td>
				<c:if test="${t.changed_no!=100}">
					이전 순위 : ${t.deck_no+t.changed_no}위
				</c:if>
				<c:if test="${t.changed_no==100}">
					신규 티어덱
				</c:if>
			</td>			
			<td><img src='./img/${t.deck_class.trim()}.png' class='class_img'></td>
			<td>정규</td>
			<td>${t.deck_name}</td>
			<td><a href='${t.deck_link}'>보러가기</a></td>
			<td>
				<form action="/HearthStone/getdeck" method="post" id="form${t.deck_no}">
					<input type="hidden" name="deck_name" value="${t.deck_name}">
					<input type="hidden" name="deck_class" value="${t.deck_class.trim()}">
					<input type="hidden" name="deck_type" value="정규">
					<input type="hidden" name="deck_link" value="${t.deck_link}">
					<%if(ob!=null){%>
						<a href="#" onclick="document.getElementById('form${t.deck_no}').submit()">저장</a>
					<%}else{ %>로그인 하세요.<%}%>
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
</section>
<script>
$(function(){
	var ctx = document.getElementById("meta_chart").getContext("2d");
	var metaChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	    	 labels: ["전사", "성기사", "사냥꾼", "드루이드", "도적", "주술사", "마법사","사제","흑마법사"],
	         datasets: [{
	             label: "현재 메타덱의 직업 수",
	             backgroundColor: ['#b23608','#e8e686','#3da53a','#bf8511','#12332e','#0331a5','#25afdd','#bcbcbc','#592f66'],
	             borderColor: 'white',
	             data: [<%=util.getClassCount("warrior")%>,<%=util.getClassCount("paladin")%>, <%=util.getClassCount("hunter")%>, <%=util.getClassCount("druid")%>,<%=util.getClassCount("rogue")%>,<%=util.getClassCount("shaman")%>,<%=util.getClassCount("mage")%>,<%=util.getClassCount("priest")%>,<%=util.getClassCount("warlock")%>],
	         }]
	
	    },
	    options: {
	    	title:{
	    		display:true,
	    		text:'<%=new Date().getYear()+1900+"년 "+(new Date().getMonth()+1)+"월 "+ (new Date().getDate())+"일 " %> 현재 직업별 메타덱 수 ',
	    		fontSize:25
	    	}
	    
	    }
    });
});
</script>
<jsp:include page="./footer.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.douzone.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 개행문자를 못쓰기 때문에 이럴 경우에만 스크립트릿 사용
	pageContext.setAttribute("newline", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
				<!-- 들어있는 값의 size 얻기 -->
				<!-- list.size()는 java 메서드이기 때문에 접근할 수 없음 -->
				<c:set var="count" value="${fn:length(list) }" />
				<c:set var="newline" value="\n" />
				<c:forEach items="${list }" var="vo" varStatus="status">
						<li>
							<table>
								<tr>
									<td>[${count -  status.index}]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate}</td>
									<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=${vo.no() }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										<!-- 표현식 안에서는 개행문자를 못쓴다. -->
										${fn:replace(vo.message, newline, "<br>") }
									</td>
								</tr>
							</table>
							<br>
						</li>
				</c:forEach>	
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>
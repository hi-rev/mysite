<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath }">배나경</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/spa">방명록(spa)</a></li>
		<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>		
	</ul>
</div>
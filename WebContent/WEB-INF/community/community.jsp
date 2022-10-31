<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<%@ include file="/WEB-INF/common/style.jspf"%>
<style>
.community {
	color: #35C5F0;
	font-weight: bold;
}

.store {
	color: black;
}

.home {
	color: #35C5F0;
	font-weight: bold;
}

.picture, .knowhow, .qna {
	color: black;
}
</style>
</head>
<body>
<div class="container">
	<c:if test="${empty id }">
		<%@ include file="/WEB-INF/common/guestMenu.jspf" %>
	</c:if>
	<c:if test="${not empty id }">
		<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
	</c:if>
	<%@ include file="/WEB-INF/common/communityMenu.jspf" %>
	<div class="container-fluid">
		<h3 class="text-center pt-4 pb-3">본문</h3>
	</div>
</div>
</body>
</html>
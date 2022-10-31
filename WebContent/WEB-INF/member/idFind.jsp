<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의집 - 아이디 찾기</title>
	<%@ include file="/WEB-INF/common/style.jspf"%>
  	<style>
  		.community, .store {
  			color: black;
  		}
  		.home, .picture, .knowhow, .qna {
  			color: black;
  		}
  		html, body {
			height: 100%;
		}
	</style>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/common/guestMenu.jspf" %>
		<div class="container-fluid">
			<h3 class="text-center pt-4 pb-3">아이디 찾기</h3>
			<form class="pb-3" action="goIdFind.do" method="post">
            	<div class="form-group text-center">
					<label for="name"><b>이름</b></label>
					<input type="text" class="form-control w-25 m-auto" id="name" placeholder="이름" name="name" required>
    			</div>
    			<div class="form-group text-center">
    				<label for="phone"><b>전화번호</b></label>
    				<input type="tel" class="form-control w-25 m-auto" id="phone" placeholder="전화번호" name="phone" required>
    			</div>
    			<div class="text-center pt-3">
    				<button type="submit" class="btn w-25" id="btn">아이디 찾기</button>
    			</div>	  
          	</form>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의집 - 로그인</title>
	<%@ include file="/WEB-INF/common/style.jspf"%>
	<style>
		html, body {
			height: 100%;
		}
		body {
			background-color: #F5F5F5
		}
	</style>
</head>
<body>
<div class="container h-100">
    <div class="row d-flex align-items-center h-100">
        <div class="col">
        	<div class="text-center pb-4">
        		<a class="text-decoration-none text-dark" href="community.do">
        			<h3 id="titleFont">모두의집</h3>
        		</a>
        	</div>
            <form class="pb-3" action="goLogin.do" method="post">
				<div class="form-group pb-2">
					<input type="text" class="form-control w-25 m-auto" id="id" placeholder="아이디" name="id">
      				<input type="password" class="form-control w-25 m-auto" id="pw" placeholder="비밀번호" name="pw">
    			</div>
    			<div class="text-center">
    				<button type="submit" class="btn w-25" id="btn">로그인</button>
    			</div>	  
          	</form>
          	<div class="text-center">
          		<div class="pb-1">
          			<a class="text-dark" href="idFind.do">아이디 찾기</a>
          		</div>
          		<div class="pb-1">
          			<a class="text-dark" href="infoConfirm.do">비밀번호 재설정</a>
          		</div>
          		<div class="pb-1">
          			<a class="text-dark" href="signup.do">회원가입</a>
          		</div>
          	</div>
        </div>
    </div>
</div>
</body>
</html>
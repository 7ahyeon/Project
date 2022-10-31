<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의집 - 비밀번호 재설정</title>
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
	<script>
		function clickBtn(frm) {
			if(frm.pw.value != frm.pw2.value) {
				alert("비밀번호 불일치");
			} else {
				frm.action = "goPwReset.do";
				frm.submit();	
			}
		}
	</script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/common/guestMenu.jspf" %>
		<div class="container-fluid">
			<h3 class="text-center pt-4 pb-3">비밀번호 재설정</h3>
			<form class="pb-3" method="post">
				<input type="hidden" id="id" name="id" value="${id }">
            	<div class="form-group text-center">
    				<label for="pw"><b>새로운 비밀번호</b></label>
    				<input type="password" class="form-control w-25 m-auto" id="pw" placeholder="비밀번호" name="pw">
    			</div>
    			<div class="form-group text-center">
    				<label for="pw2"><b>새로운 비밀번호 확인</b></label>
    				<input type="password" class="form-control w-25 m-auto" id="pw2" placeholder="비밀번호 확인" name="pw2">
    			</div>
    			<div class="text-center pt-3">
    				<button type="button" onclick="clickBtn(this.form)" class="btn w-25" id="btn">비밀번호 재설정</button>
    			</div>	  
          	</form>
		</div>
	</div>
</body>
</html>
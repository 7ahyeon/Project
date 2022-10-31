<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<%@ include file="/WEB-INF/common/style.jspf"%>
<style>
.community, .store {
	color: black;
}

.myupdate {
	color: #35C5F0;
	font-weight: bold;
}

.profile, .myshopping, .myreview {
	color: black;
}

.pwChange {
	color: #35C5F0;
	font-weight: bold;
}

.infoEdit, .withdraw {
	color: black;
}
</style>
<script>
function clickBtn(frm) {
	if(frm.pw.value != frm.pw2.value) {
		alert("비밀번호 불일치");
	} else {
		frm.action = "goPwChange.do";
		frm.submit();	
	}
}
</script>
</head>
<body>
<div class="container">
	<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
	<%@ include file="/WEB-INF/common/mypageMenu.jspf" %>
	<%@ include file="/WEB-INF/common/myupdateMenu.jspf" %>
	<div class="container-fluid">
		<h3 class="text-center pt-4 pb-3">비밀번호 변경</h3>
		<form class="pb-3" method="post">
			<div class="form-group text-center">
				<label for="pw"><b>새로운 비밀번호</b></label>
				<input type="password" class="form-control w-25 m-auto" id="pw" placeholder="비밀번호" name="pw">
			</div>
			<div class="form-group text-center">
				<label for="pw2"><b>새로운 비밀번호 확인</b></label>
    			<input type="password" class="form-control w-25 m-auto" id="pw2" placeholder="비밀번호 확인" name="pw2">
    		</div>
    		<div class="text-center pt-3">
    			<button type="button" onclick="clickBtn(this.form)" class="btn w-25" id="btn">비밀번호 변경</button>
    		</div>	  
        </form>
	</div>
</div>
</body>
</html>
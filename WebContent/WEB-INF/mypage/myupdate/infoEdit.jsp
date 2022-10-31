<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
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

.infoEdit {
	color: #35C5F0;
	font-weight: bold;
}

.pwChange, .withdraw {
	color: black;
}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function() {
	document.getElementById("addr").addEventListener("click", function(){
		new daum.Postcode({
			oncomplete: function(data) {
				document.getElementById("addr").value = data.address;
				document.querySelector("input[name=addrDetail]").focus();
			}
		}).open();
	});

	document.getElementById("btn-upload").addEventListener("click", function(e){
		e.preventDefault();
		document.getElementById("file").click();
	});
}

function clickBtn(frm) {
	frm.action = "goInfoEdit.do";
	frm.submit();
}

function changeValue(obj){
	alert(obj.value);
}

</script>
</head>
<body>
<div class="container">
	<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
	<%@ include file="/WEB-INF/common/mypageMenu.jspf" %>
	<%@ include file="/WEB-INF/common/myupdateMenu.jspf" %>
	<div class="container-fluid">
		<h3 class="text-center pt-4 pb-3">회원 정보 수정</h3>
		<form class="pb-3" method="post" name="frm">
           	<div class="form-group text-center">
				<label for="name"><b>이름</b></label>
				<input type="text" class="form-control w-25 m-auto" id="name" placeholder="이름" name="name" value="${user.name }">
    		</div>
			<div class="form-group text-center">
				<label for="id"><b>아이디</b></label>
				<input type="text" class="form-control w-25 m-auto" id="id" placeholder="아이디" name="id" value="${user.id }" readonly>
    		</div>
    		<div class="form-group text-center">
    			<label for="email"><b>이메일</b></label>
    			<input type="email" class="form-control w-25 m-auto" id="email" placeholder="이메일" name="email" value="${user.email }">
    		</div>
    		<div class="form-group text-center">
    			<label for="address"><b>주소</b></label>
    			<input type="text" class="form-control w-25 m-auto" id="addr" placeholder="주소찾기" name="addr" onkeydown="return false" value="${user.addr }">
    			<input type="text" class="form-control w-25 m-auto" id="addrDetail" placeholder="상세주소" name="addrDetail" value="${user.addrDetail }">
    		</div>
    		<div class="form-group text-center">
    			<label for="phone"><b>전화번호</b></label>
    			<input type="tel" class="form-control w-25 m-auto" id="phone" placeholder="전화번호" name="phone" value="${user.phone }">
    		</div>  
        </form>
        <form action="profileImgChange.do" method="post" enctype="multipart/form-data">
        	<div class="form-group text-center">
    			<label for="profileImg"><b>프로필 이미지</b></label><br>
    			<input type="file" id="file" name="file" style="display:none" onchange="form.submit()">   			
    			<button id="btn-upload" class="btn btn-link">
    				<img src="${pageContext.request.contextPath}/img/profileImg/${user.profileImg }" width="150px" height="150px" style="background-color: lightgray">
    			</button>    			
    		</div>
        </form>
        <div class="text-center pt-3">
    		<button type="button" onclick="clickBtn(frm)" class="btn w-25" id="btn">회원 정보 수정</button>
    	</div>	
	</div>
</div>
</body>
</html>
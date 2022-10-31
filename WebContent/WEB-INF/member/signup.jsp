<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의집 - 회원가입</title>
	<%@ include file="/WEB-INF/common/style.jspf"%>
	<style>
		html, body {
			height: 100%;
		}
	</style>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	window.onload = function() {
	    document.getElementById("addr").addEventListener("click", function(){ // 주소입력 버튼을 클릭하면
	        //카카오 지도 발생
	        new daum.Postcode({
	            oncomplete: function(data) { //선택시 입력값 세팅
	                document.getElementById("addr").value = data.address; // 주소 넣기
	                document.querySelector("input[name=addrDetail]").focus(); // 상세입력 포커싱
	            }
	        }).open();
	    });
	}
	
    function selectAll(selectAll)  {
  	  const checkboxes 
  	     = document.querySelectorAll('input[type="checkbox"]');
  	  
  	  checkboxes.forEach((checkbox) => {
  	    checkbox.checked = selectAll.checked
  	  })
  	}

	function clickBtn(frm) {
		if(frm.pw.value != frm.pw2.value) {
			alert("비밀번호 불일치");
		} else {
			frm.action = "goSignup.do";
			frm.submit();	
		}
	}
</script>
</head>
<body>
<div class="pl-3 pt-3">
	<a class="text-decoration-none text-dark" href="community.do">
		<h4 id="titleFont">모두의집</h4>
	</a>
</div>
<div class="container h-100">
    <div class="row d-flex align-items-center h-100">
        <div class="col">
        	<h3 class="text-center pb-3">회원가입</h3>
            <form class="pb-3" method="post">
            	<div class="form-group text-center">
					<label for="name"><b>이름</b></label>
					<input type="text" class="form-control w-25 m-auto" id="name" placeholder="이름" name="name" required>
    			</div>
				<div class="form-group text-center">
					<label for="id"><b>아이디</b></label>
					<input type="text" class="form-control w-25 m-auto" id="id" placeholder="아이디" name="id" required>
    			</div>
    			<div class="form-group text-center">
    				<label for="pw"><b>비밀번호</b></label>
    				<input type="password" class="form-control w-25 m-auto" id="pw" placeholder="비밀번호" name="pw" required>
    			</div>
    			<div class="form-group text-center">
    				<label for="pw2"><b>비밀번호 확인</b></label>
    				<input type="password" class="form-control w-25 m-auto" id="pw2" placeholder="비밀번호 확인" name="pw2" required>
    			</div>
    			<div class="form-group text-center">
    				<label for="email"><b>이메일</b></label>
    				<input type="email" class="form-control w-25 m-auto" id="email" placeholder="이메일" name="email" required>
    			</div>
    			<div class="form-group text-center">
    				<label for="address"><b>주소</b></label>
    				<input type="text" class="form-control w-25 m-auto" id="addr" placeholder="주소찾기" name="addr" onkeydown="return false">
    				<input type="text" class="form-control w-25 m-auto" id="addrDetail" placeholder="상세주소" name="addrDetail">
    			</div>
    			<div class="form-group text-center">
    				<label for="phone"><b>전화번호</b></label>
    				<input type="tel" class="form-control w-25 m-auto" id="phone" placeholder="전화번호" name="phone">
    			</div>
				<div class="text-center pb-2">
    				<b>약관동의</b>
    			</div>
    			<div class="border w-25 m-auto">
    				<div class="form-check text-center pb-2 pr-3">
      				<label class="form-check-label pt-3" for="checkall">
        				<input type="checkbox" class="form-check-input" name="terms" value="all" onclick="selectAll(this)">모두 동의
      				</label>
      				<hr>    				
    				<div class="form-check text-center pb-2">  				
        				<input type="checkbox" class="form-check-input" name="terms" value="age" required>만 14세 이상입니다
    				</div>
    				<div class="form-check text-center pb-2">
        				<input type="checkbox" class="form-check-input" name="terms" value="use" required>
        				<a class="text-dark" href="#"><u>이용약관</u></a>
    				</div>
    				<div class="form-check text-center pb-2">			
        				<input type="checkbox" class="form-check-input" name="terms" value="info" required>
        				<a class="text-dark" href="#"><u>개인정보수집 및 이용동의</u></a>
    				</div>
    				</div>
    			</div>
    			<div class="text-center pt-3">
    				<button type="button" onclick="clickBtn(this.form)" class="btn w-25" id="btn">회원가입하기</button>
    			</div>	  
          	</form>
          	<div class="text-center pb-1">
          		이미 아이디가 있으신가요? 
          		<a class="text-dark" href="login.do"><u>로그인</u></a>
          	</div>
        </div>
    </div>
</div>
</body>
</html>
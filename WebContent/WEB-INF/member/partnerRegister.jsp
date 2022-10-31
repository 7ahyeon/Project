<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입점신청</title>
<%@ include file="/WEB-INF/common/style.jspf"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function() {
	document.getElementById("companyAddr").addEventListener("click", function(){
		new daum.Postcode({
			oncomplete: function(data) {
				document.getElementById("companyAddr").value = data.address;
				document.querySelector("input[name=companyAddrDetail]").focus();
			}
		}).open();
	});
}
</script>
<style>
	html, body {
		height: 100%;
	}
</style>
</head>
<body>
<div class="container h-100">
    <div class="row d-flex align-items-center h-100">
        <div class="col">
        	<div class="text-center pt-5 pb-4">
        		<a class="text-decoration-none text-dark" href="community.do">
        			<h3 id="titleFont">모두의집</h3>
        		</a>
        	</div>
        	<div class="text-center pb-5">
        		<h2><b>입점신청</b></h2>
        	</div>
        	<div class="border p-3">
        		<ul style="width: 1000px; margin: 0 auto;">
        			<li>입점 신청에 대한 궁금한 사항은 오늘의집 파트너센터 가이드와 FAQ에서 확인하실 수 있습니다.</li>
        			<li>입점 검토에는 다소 시일이 소요됩니다. 검토 지연, 입점 불가의 경우 별도 회신을 드리지 못하는 점 양해 부탁드립니다.</li>
        			<li>올바른 메일 주소를 입력해주세요. 입점 승인 건에 한해, 신청서의 메일 주소로 이후 절차에 대한 안내가 발송됩니다.</li>
        			<li>중복 입점 신청이 불가능합니다. 기존 신청 건에 수정이 필요한 경우 오늘의집 파트너센터 문의하기를 통해 수정 요청 주시기 바랍니다.</li>
        		</ul>
        	</div>
            <form class="pt-5 pb-3" action="goPartnerRegister.do" method="post">
            	<div class="form-group text-center">
					<label for="companyName"><b>회사 이름</b></label>
					<input type="text" class="form-control w-25 m-auto" id="companyName" placeholder="회사 이름" name="companyName">
    			</div>
				<div class="form-group text-center">
					<label for="crn"><b>사업자 등록번호</b></label>
					<input type="number" class="form-control w-25 m-auto" id="crn" placeholder="사업자 등록번호" name="crn">
    			</div>
    			<div class="form-group text-center">
    				<label for="companyOwnerName"><b>대표자 이름</b></label>
    				<input type="text" class="form-control w-25 m-auto" id="companyOwnerName" placeholder="대표자 이름" name="companyOwnerName">
    			</div>
    			<div class="form-group text-center">
    				<label for="companyEmail"><b>회사 이메일</b></label>
    				<input type="email" class="form-control w-25 m-auto" id="companyEmail" placeholder="회사 이메일" name="companyEmail">
    			</div>
    			<div class="form-group text-center">
    				<label for="companyAddr"><b>회사 주소</b></label>
    				<input type="text" class="form-control w-25 m-auto" id="companyAddr" placeholder="주소찾기" name="companyAddr" readonly>
    				<input type="text" class="form-control w-25 m-auto" id="companyAddrDetail" placeholder="상세주소" name="companyAddrDetail">
    			</div>
    			<div class="form-group text-center">
    				<label for="companyPhone"><b>회사 전화번호</b></label>
    				<input type="tel" class="form-control w-25 m-auto" id="companyPhone" placeholder="회사 전화번호" name="companyPhone">
    			</div>
    			<div class="form-group text-center">
    				<label for="shipping"><b>배송비</b></label>
    				<input type="number" class="form-control w-25 m-auto" id="shipping" placeholder="배송비" name="shipping">
    			</div>
    			<div class="text-center pt-3">
    				<button type="submit" class="btn w-25" id="btn">판매자 신청</button>
    			</div>	  
          	</form>
        </div>
    </div>
</div>
</body>
</html>
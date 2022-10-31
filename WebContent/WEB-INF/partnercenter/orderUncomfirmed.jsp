<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터 - 주문배송 - 미확인주문</title>
<%@ include file="../common/style.jspf" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
  <style>
  .nav-link {
  	color: black;
  }
  .orderShipping, .orderUncomfirmed {
  	color: #35C5F0;
  }
  .head {
  	width:100%
  }
  .head th, td {
  	text-align: left;
  	padding: 10px;
  }
  thead th {
  	padding:30px;
  }
  input[type=text], textarea {
	width:500px;
	border: 1px solid lightgray;
	font-size: 0.8em;
	padding:5px 10px;
	}
  input[type=number] {
	width:100px;
	border: 1px solid lightgray;
	}
	input[type=date] {
	width:150px;
	border: 1px solid lightgray;
	}
	select {
		border: 1px solid lightgray;
		font-size: 0.8em;
		padding: 5px 10px;
	}
	.tb {
		width:100%;
		margin-bottom: 50px;
	}
	.tb th, .tb td {
		text-align:center;
		white-space: nowrap;
	}
	.tb td {
		font-size: 0.9em;
	}

  </style>
</head>
<body>
<%@ include file="../common/partnerMenu.jspf" %>
<%@ include file="../common/ordershippingMenu.jspf" %>
<div class="row text-center">
	<div class="col-sm" style="margin:50px 50px 0 50px;padding:20px;font-size: 0.9em;background-color: WhiteSmoke;white-space: nowrap;">
	<table class="head">
		<tbody>
			<tr>
				<td colspan="2">
					<p class="text-right">
						<span style="font-weight:bold;font-size:1.3em;">
							미확인주문
						</span>
						<br>
						<span style="font-size: 0.8em;">
							새로운 주문을 수집, 확정합니다. <br>
							선택주문 확정을 눌러 입금확인 상태로 변경해주세요.
						</span>
					</p>
					<p class="border-bottom text-muted font-weight-bold" style="font-size:0.8em;padding-bottom:10px;margin-bottom:0;">
						<span class="text-danger">
							- 개인정보는 배송 이외의 목적으로 사용을 금합니다.<br>
							- 배송처리가 지연되어 발생하는 불이익은 파트너사의 책임이니, 유의해주시기 바랍니다.<br>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<th>기간</th>
				<td>
					<input type="date" name="regdate1">
					&nbsp;~&nbsp;
					<input type="date" name="regdate2">
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">오늘</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">1주일</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">1달</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">전체</button>
				</td>
			</tr>
			<tr>
				<th>검색</th>
				<td>
					<select name="categoryName">
			            <option>주문자명</option>
			            <option value="cat">고양이</option>
			            <option value="bear">곰돌이</option>
			        </select>
					<input type="text" name="search">
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>

<div class="row">
<div class="col-sm" style="margin:0px 50px;">
<div id="container">
	<p class="font-weight-bold text-secondary" style="font-size:1em;">
		검색결과 총 <span style="color:#35C5F0;">0</span> 건
	<button class="btn btn-sm btn-primary" style="margin:20px;">선택주문 확정</button>
	</p>
	<hr style="border:1px solid #35C5F0;">
	<table class="tb">
		<thead style="background-color:#35C5F0;color:white;">
			<tr>
				<th><i class="bi bi-check2-square"></i></th>
				<th>주문번호</th>
				<th>상품번호</th>
				<th>상품명</th>
				<th>총 수량</th>
				<th>총 수량 결제액</th>
				<th>주문자명</th>
				<th>주문자 연락처</th>
				<th>배송 상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<input type="checkbox" name="chk" value="주문번호">
				</td>
				<td>주문번호</td>
				<td>
					<a href="#">상품번호</a>
				</td>
				<td>상품명</td>
				<td>총 수량</td>
				<td>총 수량 결제액</td>
				<td>주문자명</td>
				<td>주문자 연락처</td>
				<td>배송 상태</td>
			</tr>
		</tbody>
	</table>
</div>
</div>
</div>
<%@ include file="../common/footer.jspf" %>
</body>
</html>
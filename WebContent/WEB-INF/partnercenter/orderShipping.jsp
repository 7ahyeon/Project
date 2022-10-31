<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터 - 주문배송</title>
<%@ include file="../common/style.jspf" %>
  <style>
  .nav-link {
  	color: black;
  }
  .orderShipping, .orderAll {
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
	<div class="col-sm" style="margin:50px;padding:20px;font-size: 0.9em;background-color: WhiteSmoke;white-space: nowrap;">
	<table class="head">
		<tbody>
			<tr>
				<td colspan="2">
					<p class="text-right">
						<span style="font-weight:bold;font-size:1.3em;">
							전체주문
						</span>
						<br>
						<span style="font-size: 0.8em;">
							배송에 관련된 전체주문을 조회합니다.
						</span>
					</p>
					<p class="border-bottom text-muted font-weight-bold" style="font-size:0.8em;padding-bottom:10px;margin-bottom:0;">
						<span class="text-danger">
							- 개인정보는 배송 이외의 목적으로 사용을 금합니다.<br>
							- 배송처리가 지연되어 발생하는 불이익은 파트너사의 책임이니, 유의해주시기 바랍니다.<br>
						</span>
						<br>
						- 전체주문 페이지에서는 송장등록 및 수정, 배송 관리가 불가능합니다.<br>
						- 해당 기능을 원하실 경우에는 각각의 페이지로 이동해주시기 바랍니다.
					</p>
				</td>
			</tr>
			<tr>
				<th>배송상태</th>
				<td>
					<select name="categoryName">
			            <option>전체</option>
			            <option value="cat">고양이</option>
			            <option value="bear">곰돌이</option>
			        </select>
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
		<tfoot>
				<tr>
					<td colspan="2" class="text-right">
						<input type="submit" value="검색하기">
						<input type="reset" value="초기화">
					</td>
				</tr>
			</tfoot>
	</table>
	</div>
</div>

<div class="row">
<div class="col-sm" style="margin:0px 50px;">
<div id="container">
	<p class="font-weight-bold text-secondary" style="font-size:1em;">
		검색결과 총 <span style="color:#35C5F0;">0</span> 건
		
	</p>
	<hr style="border:1px solid #35C5F0;">
	<table class="tb">
		<thead style="background-color:#35C5F0;color:white;">
			<tr>
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
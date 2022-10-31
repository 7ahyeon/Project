<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터- 상품 수정 - 상세</title>
<%@ include file="../common/style.jspf" %>
  <style>
 .nav-link {
  	color: black;
  }
  .productManager, .productUpdate {
  	color: #35C5F0;
  }
  #container th {
  	font-size: 0.9em;
  	background-color: WhiteSmoke;
  	white-space: nowrap;
  }
  #container td {
  	font-size: 0.9em;
  	color: gray;
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
	select {
		border: 1px solid lightgray;
		font-size: 0.8em;
		padding: 5px 10px;
	}


  </style>
</head>
<body>
<%@ include file="../common/partnerMenu.jspf" %>
<%@ include file="../common/productMenu.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<div class="row">
	<div class="col-sm">
	</div>
	<div class="col-sm">
		<div id="container" style="margin:50px 20px;padding:20px;">
			<p class="font-weight-bold" style="font-size:1.2em;">
				기본정보
			</p>
			<form method="post" action="productInsert.do" enctype="multipart/form-data">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th>상품명</th>
							<td><input type="text" name="productName" title="상품명" value="${productUpdateList.productName }"></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select name="categoryNumber" title="카테고리" id="select1" onchange="category_select(this)">
							            <option value="">카테고리 선택</option>
									<c:forEach var="vo" items="${categoryList }">
							            <option value="${vo.categoryNum }">${vo.categoryName }</option>
							         </c:forEach>
						        </select>
						        
								<select id ="categoryDetail" title="세부 카테고리" name="categoryDetail" style="display=none;">
							            <option value="">세부 카테고리 선택</option>
						        </select>
							</td>
						</tr>
						<tr>
							<th>상품 가격</th>
							<td><input type="number" name="productPrice" title="상품 가격" value="${productUpdateList.productPrice }">&nbsp;원</td>
						</tr>
						<tr>
							<th>재고 수량</th>
							<td><input type="number" name="stock" title="재고 수량" value="${productUpdateList.stock }">&nbsp;개</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="float-right" style='margin-bottom:30px;'>
									<input type="button" value="상품 수정" onclick="sendData(this.form)">
									<input type="reset" value="초기화">
									<input type="hidden" name="productNum" value="${productUpdateList.productNum }">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>


	<div class="col-sm">
		
	</div>
</div>
<%@ include file="../common/footer.jspf" %>
<script>

	// 카테고리 ajax 처리
	function category_select(categoryN) {
		var categoryNumber = $(categoryN).val();
		
		$.ajax({
			  type:'POST',
			  url:'category.do',
			  data:{categoryNum: categoryNumber},
			  dataType : "json",
			  success: function(args) {
				let alist = args.list;
				let str = "";
				str +=  "<option value=''>세부 카테고리 선택</option>";
				$.each(alist, function(index, vo){
					if (categoryNumber == this.categoryNum){
						str += "<option value='" + this.categoryDetail + "'>" + this.categoryDetail + "</option>";
					}
				});
				$("#categoryDetail").html(str);
			  },
			  error : function(jqXHR, textStatus, errorThrown) {
			  }
		});
					
	}
	
	
	
	function sendData(tfm) {
		
		let firstForm = document.forms[0];
		let formNum = firstForm.elements.length - 2;
		
		// 빈칸 존재시 상품 등록 불가
		for (let i = 0; i < formNum; i++) {
			var title = firstForm.elements[i].title;
			
			if (firstForm.elements[i].value.trim() == "" ) {
				if (i == 0 || i == 3 || i == 4){
					Swal.fire(title + '을 입력하세요.');
					return;
				} else if (i == 1 || i == 2){
					Swal.fire(title + '를 선택하세요.');
					return;
				}
				firstForm.elements[i].value = "";
				firstForm.elements[i].focus();
				return;
			}
			
		}
		// 가격 / 재고 값 제한
		var productPriceVal = firstForm.elements[3].value;
		var stockVal = firstForm.elements[4].value;
		if (productPriceVal > 9999999 || productPriceVal < 0){
			Swal.fire('가격은 0원부터 9999999까지 등록 가능합니다.');
			return;
		}else if (stockVal > 9999999 || productPriceVal < 0){
			Swal.fire('재고는 0개부터 9999999개까지 등록 가능합니다.');
			return;
		}

		// 파일 서버 전송
		
	    var productInputList = {
	    	productName : firstForm.elements[0].value,
	    	categoryNum : firstForm.elements[1].value,
	    	categoryDetail : firstForm.elements[2].value,
	    	productPrice : firstForm.elements[3].value,
	    	stock : firstForm.elements[4].value,
	    	productNum : firstForm.elements[7].value
	    };
	    $.ajax({
			  type:'POST',
			  url:'productUpdateGo.do' ,
			  async:false,
			  data: productInputList,
			  success: function(data) {
				  Swal.fire(
						  '상품 수정 완료',
						  firstForm.elements[0].value + ' 상품이 성공적으로 수정되었습니다.',
						  'success'
				)
			  },
			  error : function(jqXHR, textStatus, errorThrown) {
			  }
	    });
	}
	
	
</script>
</body>
</html>
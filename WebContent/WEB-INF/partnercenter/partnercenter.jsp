<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터</title>
<%@ include file="../common/style.jspf" %>
  <style>
 .nav-link {
  	color: black;
  }
  .productManager, .product {
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
							<td><input type="text" name="productName" title="상품명" placeholder="상품명 입력"></td>
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
							<td><input type="number" name="productPrice" title="상품 가격"  placeholder="0">&nbsp;원</td>
						</tr>
						<tr>
							<th>재고 수량</th>
							<td><input type="number" name="stock" title="재고 수량" placeholder="0">&nbsp;개</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="container" style="padding:20px;">
									<p class="font-weight-bold" style="font-size:1.2em;">
										이미지
									</p>
									<div style="padding:20px;font-size:0.8em;">
										<p class="text-danger">
											※ 이미지 업로드 가이드 ※
										</p>
										<p class="text-muted">
											사이즈: 대표 이미지는 정사각형 이미지를 사용해주세요.(권장: 1440픽셀 X 1440픽셀, 1080픽셀 X 1080픽셀)
											<br>용량: 이미지는 최대 10MB까지 사용 가능합니다.
											<br>확장자: JPG, JPEG, PNG, GIF 파일에 한해 등록 가능합니다.
										</p>
										<hr>
										<div class="d-flex flex-row">
											<div class="p-2">
												<p class="font-weight-bold" style="font-size:1.2em;">
													대표 이미지
												</p>
												<div style="padding:0px 10px 10px 0px;">
													<span class="text-muted">
													대표 이미지는 첫번째 또는 상품 리스트에 노출할 이미지를 의미합니다.
													</span>
													<p></p>
													<div class="input-group">
												        <div class="custom-file">
												            <input type="file" onchange="fileCheck(this);" name="imageFile" title="대표 이미지" accept=".jpg, .jpeg, .png, .gif" class="custom-file-input" id="productImg">
												            <label class="custom-file-label" for="productImg" style="width:300px;">선택된 파일 없음</label>
												        </div>
												     </div>
										    		<img class="preview" id="preview0" style="width:275px;height:275px;margin-top:10px;"/>
												</div>
											</div>
											<div class="p-2">
												<p class="font-weight-bold" style="font-size:1.2em;">상세 정보 이미지</p>
												<div style="padding:0px 20px 20px 0px;">
													<span class="text-muted">
														상세 정보 이미지는 상품 상세정보에 노출할 이미지를 의미합니다.
													</span>
													<p></p>
													<div class="input-group">
												        <div class="custom-file">
												            <input type="file" onchange="fileCheck(this);" name="productInfo"  title="상세 이미지" accept=".jpg, .jpeg, .png, .gif" class="custom-file-input" id="productInfoImg">
												            <label class="custom-file-label" for="productInfoImg" style="width:300px;">선택된 파일 없음</label>
												        </div>
												     </div>
										    		<img class="preview" id="preview1" style="width:275px;height:275px;margin-top:10px;"/>
												</div>
											</div>
										</div>
									</div>
									<div class="float-right" style='margin-bottom:30px;'>
										<input type="button" value="상품 등록" onclick="sendData(this.form)">
										<input type="reset" value="초기화">
									</div>
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
						str += "<option value='" + this.categoryDetailNum + "'>" + this.categoryDetail + "</option>";
					}
				});
				$("#categoryDetail").html(str);
			  },
			  error : function(jqXHR, textStatus, errorThrown) {
			  }
		});
					
	}
	
	// 대표 이미지 / 상세정보 이미지
	function fileCheck(input) {

		var fileList = input.files;
		var fileObj = fileList[0];
		
		var fileName = fileObj.name;
		var fileSize = fileObj.size;
		
		if (input.id == 'productImg'){
			
			// 대표 이미지 최대 용량 10MB
			var maxSize = 10 * 1024 * 1024;
			
			// 파일 이름 표시 처리
			var pr = 'preview0';
			$("#productImg").siblings(".custom-file-label").addClass("selected").html(fileName);
		} else if (input.id == 'productInfoImg'){
			
			// 상세 이미지 최대 용량 10MB
			var maxSize = 10 * 1024 * 1024; 
			
			// 파일 이름 표시 처리
			var pr = 'preview1';
			$("#productInfoImg").siblings(".custom-file-label").addClass("selected").html(fileName);
		}
		
		// 파일 용량 제한
		
		if(fileSize >= maxSize) {
			sizeError();
			return false;
		}
		
		// 파일 preview
		if (fileList && fileObj) {
			var reader = new FileReader();
			reader.readAsDataURL(fileObj);
			reader.onload = function(e) {
				document.getElementById(pr).src = e.target.result;
			};
		} else {
			document.getElementById(pr).src = "";
			return false;
		}
		
		
		
	}
	
	function sizeError(){
		Swal.fire({
			  icon: 'error',
			  title: '파일 용량 초과',
			  text: '확인 후 다시 업로드해주세요.',
			})
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
				} else if (i == 5 || i == 6) {
					Swal.fire(title + '를 업로드하세요.');
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
	    	stock : firstForm.elements[4].value
	    };
	    var productNumber;
	    $.ajax({
			  type:'POST',
			  url:'productInsert.do',
			  async:false,
			  data: productInputList,
			  dataType : "json",
			  success: function(args) {
				  productNumber = args.productNum;
			  },
			  error : function(jqXHR, textStatus, errorThrown) {
			  }
	    });
	
	    let productImgFileList = $("#productImg")[0].files;
		let productInfoImgFileList = $("#productInfoImg")[0].files;
		
		let productImgFileObj = productImgFileList[0];
		let productInfoImgFileObj = productInfoImgFileList[0];
		
		var productImgFormData = new FormData();
		var productInfoImgFormData = new FormData();
		
		productImgFormData.append('productNum', productNumber);
		productImgFormData.append('uploadFile', productImgFileObj);
		productInfoImgFormData.append('productNum', productNumber);
		productInfoImgFormData.append('uploadFile', productInfoImgFileObj);
		
	    $.ajax({
			method : "POST",
			url : 'productImgInsert.do',
			processData: false, 
	  		contentType: false, 
			data : productImgFormData,
			success: function(data) {
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
	    });
	    $.ajax({
			method : "POST",
			url : 'productInfoImgInsert.do',
			processData: false, 
	  		contentType: false, 
			data : productInfoImgFormData,
			success: function(data) {
				Swal.fire(
						  '상품 등록 완료',
						  firstForm.elements[0].value + ' 상품이 성공적으로 등록되었습니다.',
						  'success'
				)
			},
			  error : function(jqXHR, textStatus, errorThrown) {
				  alert("상품 등록 실패");
			  }	
	    });
	    
	}
	
	
</script>
</body>
</html>
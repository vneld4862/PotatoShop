//주소창 및 search 버튼 클릭 시 실행
function searchTrAddr(){
	new daum.Postcode({
		        oncomplete: function(data) {
		            //도로명 주소
		        	 const roadAddr = data.roadAddress; 
		             document.querySelector('#tradeAddr').value = roadAddr;//내가 선택한 input태그에 밸류값으로 도로명주소를 넣어준다.
		             
		        }
		    }).open();
	
}

//서브 이미지  목록 삭제(버튼 클릭 시)
function deleteImg(deleteBtn){
	alert('이미지 삭제 구현하기');
	const imgCode = deleteBtn.dataset.imgCode;
	const itemCode = deleteBtn.dataset.itemCode;
	alert(itemCode);
	alert(imgCode);
	
	//ajax start
	$.ajax({
	   url: '/manage/deleteImg', //요청경로
	    type: 'post',
	    data:{'imgCode':imgCode, 'itemCode':itemCode}, //필요한 데이터
	    success: function(itemInfo) {
		
	      alert('이미지 삭제 성공');
	      alert(itemInfo.itemCode);
	      $(deleteBtn).closest('div').remove(); //j쿼리문법 **** 
	      
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	//ajax end
	
}


//메인 이미지 삭제
function deleteMainImg(){
	alert('메인이미지 이미지 삭제');
	const imgCode = document.querySelector('#mainImgCode').value;
	alert(imgCode)
	$('#mainDiv').remove(); //j쿼리문법 **** 
	
	//ajax start
	$.ajax({
	   url: '/manage/deleteImg', //요청경로
	    type: 'post',
	    data:{'imgCode':imgCode, 'itemCode':itemCode}, //필요한 데이터
	    success: function(itemInfo) {
	      alert('이미지 삭제 성공');
	      alert(itemInfo.itemCode);
	      /*여기서 그림 다시 그려주기 구현...*/
	     
	      
	      $(deleteBtn).closest('div').remove(); //j쿼리문법 **** 
	      
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	//ajax end
	
}


//메인 이미지 미리보기(파일 선택 시)
function readURL(input) {
    $('.mainImgDiv div.loadMainDiv').remove();
  if (input.files && input.files[0]) {
	//원본이미지를 db에서 삭제
    //  deleteImg(deleteBtn);이렇게 함수를 적어서 실행시켜주면 된다.
    
    

    var reader = new FileReader();
    reader.onload = function(e) {
			let str = '';
			str += '<div class="col-6 mainDiv loadMainDiv">';
			str += '[수정될 메인 이미지]';
			str += `<img src=${e.target.result} width="230px;" height="230px;">`;
			str += '</div>';
			$('.mainImgDiv').append(str);

	
	//	document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
    
  } else {
	
  }
}

//서브 이미지 미리보기(파일 선택 시)
function readURL2(input) {
	$('.allSubImgDiv div:not(.loadImgDiv)').remove();
	
	for(const img_file of input.files){
		if (input.files && img_file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				let str = '';
				str += '<div class="col-3 mb-3 subDiv">';
				str += `	<img src=${e.target.result} width="210px;" height="210px;"><br>`;
				str += `	<input type="hidden" th:value="">`
				str += '</div>';
				$('.allSubImgDiv').append(str);
				
			};
			reader.readAsDataURL(img_file);
		} else {
			
		}
	}
}

//서브이미지 미리보기 삭제
function delSubPreview(deleteBtn){
	alert('서브 미리보기 삭제');
	$(deleteBtn).closest('div').remove(); //j쿼리문법 **** 
	
	
}

////validation 구현 중
function updateItem(){
	let submitBoolean = true;
	
	//상품명		
	if($("#itemName").val() == ""){
		$(".nameChk").text("※ 상품명은 필수입력입니다");
		$(".nameChk").css("color", "red");
		submitBoolean = false;
	} 
	//카테고리
	if($("#mainCateCode").val() == ""){
		$(".cateChk").text("※ 최소 한개 이상의 카테고리를 등록해주세요");
		$(".cateChk").css("color", "red");
		submitBoolean = false;

	}
	//상품가격
	if($("#itemPrice").val() == ""){
		$(".priceChk").text("※ 올바른 가격을 입력해주세요");
		$(".priceChk").css("color", "red");
		submitBoolean = false;

	}
	
	//거래주소
	if($("#tradeAddr").val() == ""){
		$(".addrChk").text("※ 주소창은 필수입력입니다");
		$(".addrChk").css("color", "red");
		submitBoolean = false;

	}
	
	
	if(submitBoolean){
		
		
		$("#ItemForm").submit();

	}
	
}








//카테고리 
const mainSelect=document.querySelector('.mainCate select');
const subCate=document.querySelector('.subCate');
const detailCate=document.querySelector('.detailCate');
mainSelect.addEventListener('change',function(){
	const mainCateCode=mainSelect.value;
	$.ajax({
	   url: '/cate/selectSubCateAjax', //요청경로
	    type: 'post',
	    data:{'mainCateCode':mainCateCode}, //필요한 데이터
	    success: function(result) {
			subCate.innerText='';
			detailCate.innerText='';
			let str='';
			str+=`<select name='subCateCode' class="form-select form-select-sm mt-2">`
			str+='<option value="">선택해주세요.</option>';
			for(const subCate of result)
			{
				str+=`<option value=${subCate.subCateCode}>${subCate.subCateName}</option>`;
			}
			str+=`</select>`;
			if(!result.length==0)
			{
				subCate.insertAdjacentHTML('afterbegin',str);
				subCateList();
			}
	    },
	    error: function(){
	       alert('실패');
	    }
	});
})

function subCateList(){
	const subCateSelect=subCate.querySelector('select');
	subCateSelect.addEventListener('change',function(){
		const subCateCode=subCateSelect.value;
		
		$.ajax({
	   url: '/cate/selectDetailCateAjax', //요청경로
	    type: 'post',
	    data:{'subCateCode':subCateCode}, //필요한 데이터
	    success: function(result) {
			detailCate.innerText='';
			let str='';
			str+=`<select name='detailCateCode' class="form-select form-select-sm mt-2">`
			str+='<option value="">선택해주세요.</option>';
			for(const detailCate of result)
			{
				str+=`<option value=${detailCate.detailCateCode}>${detailCate.detailCateName}</option>`;
			}
			str+=`</select>`;
			if(!result.length==0)
			{
				detailCate.insertAdjacentHTML('afterbegin',str);
			}
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	})
}
function searchTrAddr(){
	new daum.Postcode({
		        oncomplete: function(data) {
		            //도로명 주소
		        	 const roadAddr = data.roadAddress; 
		             document.querySelector('#tradeAddr').value = roadAddr;//내가 선택한 input태그에 밸류값으로 도로명주소를 넣어준다.
		             
		        }
		    }).open();
	
}

//메인 이미지 미리보기(파일 선택 시)
function readURL(input) {
    $('.mainImgDiv div.mainDiv').remove();
  if (input.files && input.files[0]) {
    
    

    var reader = new FileReader();
    reader.onload = function(e) {
			let str = '';
			str += '<div class="col-10 mainDiv">';
			str += `	<div>[등록될  메인 이미지]</div>`;
			str += `	<div>`;
			str += `		<img src=${e.target.result} width="230px;" height="230px;">`;
			str += '	</div>';
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
	$('.loadImgDiv').remove();
	//$('.allSubImgDiv div:not(.loadImgDiv)').remove();
	
	for(const img_file of input.files){
		if (input.files && img_file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				let str = '';
				str += '<div class="col-3 subDiv mb-3">';
				str += `	<img src=${e.target.result} width="210px;" height="210px;">`;
				str += '</div>';
				$('.allSubImgDiv').append(str);
				
			};
			reader.readAsDataURL(img_file);
		} else {
			
		}
	}
}


//카테고리 구현
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

function regItem(){
	const mainImg = document.querySelector('#mainImgTag').value;
	const itemName = document.querySelector('#itemName').value;
	alert(itemName);
	if(mainImg == ""){
		$(".mainImgChk").text("※ 메인 이미지는 필수입력입니다");
		$(".mainImgChk").css("color", "red");
		return ;
	}
	
	//상품명		
	if($("#itemName").val() == ""){
		$(".nameChk").text("※ 상품명은 필수입력입니다");
		$(".nameChk").css("color", "red");
		submitBoolean = false;
	} 
	document.querySelector('#regItemForm').submit();
}
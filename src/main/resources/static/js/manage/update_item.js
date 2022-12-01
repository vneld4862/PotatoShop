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

//서브이미지 미리보기 삭제 



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
			str += '<div class="col-3 mainDiv loadMainDiv">';
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
				str += '<div class="col-3 subDiv">';
				str += `<img src=${e.target.result} width="230px;" height="230px;"><br>`;
				str += `<input type="hidden" th:value="">`
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
alert('이미지 수정 해보기');

function deleteImg(deleteBtn){
	alert('이미지 삭제 구현하기');
	const imgCode = deleteBtn.dataset.imgCode;
	const itemCode = deleteBtn.dataset.itemCode;
	alert(itemCode);
	//ajax start
	$.ajax({
	   url: '/manage/deleteImg', //요청경로
	    type: 'post',
	    data:{'imgCode':imgCode, 'itemCode':itemCode}, //필요한 데이터
	    success: function(itemInfo) {
	      alert('이미지삭제성공');
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


//메인 이미지 수정
/*function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}*/


//메인 이미지 수정
function readURL(input) {
	$('.mainImgDiv').remove();
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function(e) {
		let str = '';
		str += '<div class="col-3">';
		str += `<img src=${e.target.result} width="230px;" height="230px;">`;
		str += '</div>';
		$('.mainImgDiv').append(str);
	
	
     // document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById('preview').src = "";
  }
}

//서브 이미지 수정
function readURL2(input) {
	$('.allSubImgDiv div:not(.loadImgDiv)').remove();
	
	for(const img_file of input.files){
		if (input.files && img_file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				let str = '';
				str += '<div class="col-3 imgDiv testImg">';
				str += `<img src=${e.target.result} width="230px;" height="230px;"><br>`;
				str += `<input type="hidden" th:value="">`
				str += `<button type="button" class="testBtn"`
				str += `th:onclick="deleteImg(this);"`
				str += `th:data-img-code=""`
				str += `th:data-item-code="">x</button>`
				str += '</div>';
				$('.allSubImgDiv').append(str);
				
			};
			reader.readAsDataURL(img_file);
		} else {
			//document.getElementById('preview').src = "";
			document.querySelectorAll('.preview').src = "";
		}
	}
}
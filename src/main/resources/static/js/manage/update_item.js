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
alert("삭제기능해보자ㅜ");

function deleteItem(itemCode){
	//alert(itemCode);
	
	//ajax start
	$.ajax({
	    url: '/manage/deleteItem', //요청경로
	    type: 'post',
	    data:{'itemCode' : itemCode}, //필요한 데이터
	    success: function(result) {
	      alert('삭제성공');
	    },
	    error: function(){
	       alert('삭제실패');
	    }
	});
	//ajax end
}
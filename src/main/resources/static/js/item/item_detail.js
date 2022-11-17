//alert('상품 상세화면입니당~ 히히');

function insertWish(wishBtn){
//	alert('찜꽁');
	const itemCode = wishBtn.dataset.itemCode;
//	alert(itemCode);
	
//	const itemCode2 = document.querySelector('#itemCodeInput').value;
//	alert(itemCode2);

	
	$.ajax({
	   url: '/wish/insertWish', //요청경로
	    type: 'post',
	    data:{'itemCode': itemCode}, //필요한 데이터
	    success: function(result) {
	      alert('해당 상품을 찜했습니다.');
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
	
	
};


alert('상품 상세화면입니당~ 히히');

function insertWish(wishBtn){
	alert('찜꽁');
	const itemCode = wishBtn.dataset.itemName;
	alert(itemCode);
	
	const itemCode2 = document.querySelector('#itemCodeInput').value;
	alert(itemCode2);
	//ajax start
	$.ajax({
	   url: '/item/insertWish', //요청경로
	    type: 'post',
	    data:{'itemCode': itemCode2}, //필요한 데이터
	    success: function(result) {
	      alert('aaa');
	    },
	    error: function(){
	       alert('실패');
	    }
	});
//ajax end
	
	
	
};


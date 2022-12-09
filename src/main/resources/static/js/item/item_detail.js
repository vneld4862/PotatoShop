//찜버튼 클릭 시
function insertWish(wishBtn){
	const itemCode = wishBtn.dataset.itemCode;
	
//	const itemCode = document.querySelector('#itemCodeInput').value;
//	alert(itemCode);

	
	$.ajax({
	   url: '/wish/insertWish', //요청경로
	    type: 'post',
	    data:{'itemCode': itemCode}, //필요한 데이터
	    success: function(wishCode) {
		//	 alert(wishCode);
			 if(wishCode == ''){
				//alert('해당 상품을 찜목록에서 제거했습니다.');
			  }
			 else{
				//alert('해당 상품을 찜목록에 추가하였습니다.');
			  }
	         location.href = `/item/itemDetail?itemCode=${itemCode}`;
	    },
	    error: function(){
	       alert('실패');
	    }
	});

	
	
	
};
const regDate=document.querySelector('#regDate').value;
const now=new Date();
const start = new Date(regDate);

const timeDiff = now.getTime() - start.getTime();
const day = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
if(day==0)
{
	$('#insertDate').text('오늘');
}
else
{
	$('#insertDate').text(day + '일 전');
}
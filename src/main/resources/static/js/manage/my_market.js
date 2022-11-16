//모달 창 선택
const reviewDetailModal = new bootstrap.Modal('#reviewDetailModal');

//리뷰 제목 클릭하면 실행
function reviewDetail(itemCode){
	//ajax start
	$.ajax({
		url: '/board/reviewDetail', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
		success: function(result) {
			document.querySelector('#reviewDetailModal_itemName').innerText = result.itemVO.itemName;
			document.querySelector('#reviewDetailModal_starPoint').innerText = result.starPoint;
			document.querySelector('#reviewDetailModal_title').innerText = result.boardTitle;
			document.querySelector('#reviewDetailModal_wirter').innerText = result.memberId;
			document.querySelector('#reviewDetailModal_pic').innerText = result;
			reviewDetailModal.show();
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}
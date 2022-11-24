//모달 창 선택
const reviewModal = new bootstrap.Modal('#review_modal');

//후기 작성 버튼을 누르면 실행되는 함수
function regReview(selectedTag) {

	const itemName = selectedTag.closest('tr').querySelector('#itemName').dataset.itemCode;
	//const itemName = selectedTag.closest('td').querySelector('#hiddenItemName').value;
	
	const itemCode = selectedTag.closest('td').querySelector('#hiddenItemCode').value;
	document.querySelector('#modalItemName').innerText = itemName;
	document.querySelector('#modalItemCode').value = itemCode;
	
	//모달 실행
	reviewModal.show();
}


//구매 확정 버튼을 누르면 실행되는 함수
function isConfirmed(selectedTag) {
	
	const itemCode = selectedTag.closest('td').querySelector('#hiddenItemCode').value;
	
	const result = confirm('구매 확정 하시겠습니까?', itemCode);
	
	if(result){
		//ajax start
		$.ajax({
			url: '/buy/buyConfirm', //컨트롤러로 가야 쿼리 실행 가능
			type: 'post',
			data: {'itemCode': itemCode},
			success: function(result) {
				alert('구매 확정이 완료되었습니다.');
				location.href="/manage/buyList"
			},
			error: function() {
				alert('실패');
			}
		});
		//ajax end
	}
	
	
}
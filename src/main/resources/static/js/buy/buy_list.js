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
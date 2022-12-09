//전체선택 checkbox
function checkAll() {
   if($("#AllCbox").is(':checked')) {
      $("input[name=checkbox]").prop("checked", true);
   } else {
      $("input[name=checkbox]").prop("checked", false);
   }
}

$(document).on("click", "input:checkbox[name=checkbox]", function(e) {
   
   var chks = document.getElementsByName("checkbox");
   var chksChecked = 0;
   
   for(var i=0; i<chks.length; i++) {
      var cbox = chks[i];
      
      if(cbox.checked) {
         chksChecked++;
      }
   }
   
   if(chks.length == chksChecked){
      $("#AllCbox").prop("checked", true);
   }else{
      $("#AllCbox").prop("checked",false);
   }
   
});


//선택삭제 버튼 클릭 시 실행
function deleteItem(){
	alert(1111);
	const itemCodesForm = document.querySelector('#itemCodesForm');
	const checkBoxes = document.querySelectorAll('.checkbox:checked');
	
	if(checkBoxes.length == 0){
		alert('선택된 상품이 없습니다.\n 상품을 먼저 선택해주세요.')
		return;
	}
	
	let itemCodes = '';
	for(const checkBox of checkBoxes){
		const itemCode = checkBox.value;
		itemCodes = itemCodes + itemCode + ',';
		
	}
	
	itemCodesForm.querySelector('input[type="hidden"]').value = itemCodes;
	itemCodesForm.action = "/manage/deleteItem";
	itemCodesForm.submit();
}

//판매상태 변경 시
function changeStatus(statSelect){
	const salesStatus = statSelect.value;
	const itemCode = statSelect.dataset.itemCode;
	
	$.ajax({
	   url: '/manage/salesStatus', //요청경로
	    type: 'post',
	    data:{'salesStatus':salesStatus, 'itemCode':itemCode}, //필요한 데이터
	    success: function(salesStatus) {
	      alert('판매상태를 변경하였습니다.');
	    },
	    error: function(){
	       alert('업데이트 실패');
	    }
	});
	
}

//상품 삭제 버튼 클릭 시
function deleteCheck(){
	//모달 -> 확인 : 삭제
	        //취소: 모달지우기
	 alert('삭제체크');
}






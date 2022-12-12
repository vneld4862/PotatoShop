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
	const itemCodesForm = document.querySelector('#itemCodesForm');
	const checkBoxes = document.querySelectorAll('.checkbox:checked');
	
	if(checkBoxes.length == 0){
		alert('선택된 상품이 없습니다.\n 상품을 먼저 선택해주세요.')
		return;
	}
	
	const result = confirm('해당 상품을 삭제하시겠습니까?');
	if(result){
		let itemCodes = '';
		for (const checkBox of checkBoxes) {
			const itemCode = checkBox.value;
			itemCodes = itemCodes + itemCode + ',';

		}

		itemCodesForm.querySelector('input[type="hidden"]').value = itemCodes;
		itemCodesForm.action = "/manage/deleteItem";
		itemCodesForm.submit();
		
	}
	
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
	      //alert('판매상태를 변경하였습니다.');
	       Swal.fire(
                    'ok',
                    '판매상태가 변경되었습니다.',
                    'success'
                    )
	    },
	    error: function(){
	       alert('업데이트 실패');
	    }
	});
	
}

//상품 삭제 버튼 클릭 시
function deleteCheck(deleteBtn){
	 const itemCode  = deleteBtn.dataset.itemCode;
	  Swal.fire(
                   { title: '해당 상품을 삭제하시겠습니까?',
				 //	 text: '다시 되돌릴 수 없습니다. 신중하세요.',
				  	 icon: 'warning',
				   
				  	 showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
				  	 confirmButtonColor: '#367E18', // confrim 버튼 색깔 지정
				  	 cancelButtonColor: '#B2B2B2', // cancel 버튼 색깔 지정
				  	 confirmButtonText: '삭제', // confirm 버튼 텍스트 지정
				  	 cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                   }).then(result => {
   // 만약 Promise리턴을 받으면,
	   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
	   
	   		location.href = '/manage/deleteItem?itemCode='+ itemCode;
	   		
	   }
});
                   
	 
}






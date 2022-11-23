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











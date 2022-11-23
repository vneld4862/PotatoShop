//alert("상품관리 화면연결~");
function checkAll() {
   if($("#AllCbox").is(':checked')) {
      $("input[name=checkboxes]").prop("checked", true);
   } else {
      $("input[name=checkboxes]").prop("checked", false);
   }
}

$(document).on("click", "input:checkbox[name=checkboxes]", function(e) {
   
   var chks = document.getElementsByName("checkboxes");
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


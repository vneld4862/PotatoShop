
function searchId(){
	var name=$('#memberName').val()
	var phone=$('#memberTell').val()
	
	
			//ajax start
			$.ajax({
			   url: '/member/doSearchId', //요청경로
			    type: 'post',
			    data:{"name":name, "phone":phone}, //필요한 데이터
			    success: function(result) {
				
				  //모달창 띄우기
			       const modal = new bootstrap.Modal('#searchIdModal');
			       modal.show();
				     
			      
			    },
			    error: function(){
			       alert('실패');
			    }
				});
			//ajax end
	
	}
 

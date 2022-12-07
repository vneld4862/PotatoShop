
$("#pwBtn").click(function() {
	$('#searchPw_result').empty();
	let str = '';
	
	const email = $("#memberEmail").val();
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	const memberId = $("#memberId").val();
	
	if(!regExp.test(email)){
		str += '<span>이메일을 정확히 입력해주세요</span>';
		$('#searchPw_result').append(str);

		//모달창 띄우기
		const modal = new bootstrap.Modal('#searchPwModal');
		modal.show();
		
		return ;
	}
	else{
		str +=  `<div>
					<h4>${email}</h4>
					<span> 이메일로 임시 비밀번호를 발송하였습니다!</span><br><br>
				 </div>`;
		$('#searchPw_result').append(str);
	}
	
 
	 //ajax start
		$.ajax({
		    url: '/member/sendEmail', //요청경로
		    type: 'post',
		    data:{"memberEmail": email, 'memberId' : memberId}, //필요한 데이터
		    success: function(result) {
				//모달창 띄우기
				const modal = new bootstrap.Modal('#searchPwModal');
				modal.show();
		    },
		    error: function(){
		       alert('실패');
		    }
		});
		//ajax end
 	
 })







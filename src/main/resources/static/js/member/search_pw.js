
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
					<span> 비밀번호 전송 완료!</span><br><br>
					<h6><div>※ 가입한 적이 없는 이메일 주소나 올바르지 않은 이메일 주소를<br> 입력하신 경우에는 메일을 받을 수 없습니다.</div></h6>
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

 
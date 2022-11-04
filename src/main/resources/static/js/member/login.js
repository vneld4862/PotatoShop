
function login(){
	const member_id = document.querySelector('#memberId').value;
	const member_pw = document.querySelector('#memberPw').value;
	
	//ajax start
	$.ajax({
		url: '/member/ajaxLogin', //요청경로
		type: 'post',
		data: {'memberId':member_id, 'memberPw':member_pw}, //필요한 데이터
		success: function(result) {
			if(result){
				alert('로그인 성공');
				location.href='/item/list'; 
			}
			else{
				alert('로그인 실패');
			}			
		},
		error: function() {
			alert('로그인 실패');
		}
	});
	}//ajax end
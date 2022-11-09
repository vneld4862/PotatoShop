//주소 버튼 클릭시 진행
function searchAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	const roadAddr = data.roadAddress; 
        	document.querySelector('#memberAddr').value = roadAddr;
        }
    }).open();
}

//중복확인 버튼 클릭시 진행
function check(){
	const member_id = $("#memberId").val();
	
	//ajax start
	$.ajax({
		url: '/member/checkDuplId', //요청경로
		type: 'post',
		data: {'memberId':member_id}, //필요한 데이터
		success: function(result) {
			if(result){
				alert('사용가능한 아이디입니다.');
			}
			else{
				alert('이미 사용중인 아이디가 존재합니다');
			}
		},
		error: function() {
			alert('로그인 실패');
		}
	});
	//ajax end
}

//join 버튼 클릭시 진행
function join(){
	const member_id = $("#memberId").val();
	
	//ajax start
	$.ajax({
		url: '/member/checkDuplId', //요청경로
		type: 'post',
		data: {'memberId':member_id}, //필요한 데이터
		success: function(result) {
			if(result){
				alert('로그인 성공');
			}
			else{
				alert('로그인 실패');
			}
		},
		error: function() {
			alert('로그인 실패');
		}
	});
	//ajax end
	
}



		
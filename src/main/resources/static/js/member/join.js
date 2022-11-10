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
	if(member_id == "" || member_id.length < 2){
    		$(".successNameChk").text("이름은 2자 이상 8자 이하로 설정해주세요 :)");
			$(".successNameChk").css("color", "red");
	}else{
	
		//ajax start
		$.ajax({
			url: '/member/checkDuplId', //요청경로
			type: 'post',
			data: {'memberId':member_id}, //필요한 데이터
			success: function(result) {
				if(result){
					$(".successNameChk").text(" 사용가능한 아이디입니다.");
					$(".successNameChk").css("color", "green");
				}
				else{
					$(".successNameChk").text(" 이미 사용중인 아이디입니다.");
					$(".successNameChk").css("color", "red");
				}
			},
			error: function() {
				alert('로그인 실패');
			}
		});
		//ajax end
}
}

//비밀번호 확인
function passChk(){
	
		if($("#userpasschk").val() == $("#memberPw").val()){
			$(".successPwChk").text("비밀번호가 일치합니다.");
			$(".successPwChk").css("color", "green");
		}else{
			$(".successPwChk").text("비밀번호가 일치하지 않습니다.");
			$(".successPwChk").css("color", "red");
		}

}






		
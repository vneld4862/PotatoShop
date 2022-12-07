
//로그인 validation
function loginBtn(){
	
	const member_id = $("#memberId").val();
	const member_pw = $("#memberPw").val();
	
	if(member_id == "" || member_pw == ""){
		if(member_id == ""){
    		$(".successIdChk").text("아이디를 입력해주세요");
			$(".successIdChk").css("color", "red");
		}
		if(member_pw == ""){
			$(".successPwChk").text("비밀번호를 입력해주세요");
			$(".successPwChk").css("color", "red");
		}	
		return ;
	}
	document.querySelector('#loginForm').submit();
}

   



		
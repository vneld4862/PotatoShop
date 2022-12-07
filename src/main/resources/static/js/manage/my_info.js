//탈퇴하기 버튼 클릭 시 실행
function deleteMember(memberId){
	
	const result = confirm('정말 탈퇴하시겠습니까?', memberId);
	
	if(result) {
		//ajax start
		$.ajax({
			url: '/member/deleteMember', //요청경로
			type: 'post',
			data: {'memberId':memberId}, //필요한 데이터 를 가지고 컨트롤러로 가서 성공하면 밑에 석세스문이 실행
			success: function(result) {
				alert('탈퇴가 완료되었습니다.');
				location.href="/logout"
			},
			error: function() {
				alert('실패');
			}
		});
		//ajax end
	}
}

//수정하기 버튼 클릭 시 실행
function updateBtn(){
	alert(222);
	//
	let submitBoolean = true;
	
	
	
	
		if($("#memberTell").val() == ""){
			$(".tellChk").text("※ 휴대폰은 필수입력입니다");
			$(".tellChk").css("color", "red");
			submitBoolean = false;
		} 
		if($("#memberEmail").val() == ""){
			$(".emailChk").text("※ 이메일은 필수입력입니다");
			$(".emailChk").css("color", "red");
			submitBoolean = false;

		}
		
		
		if(submitBoolean){
			
			
			$("#myInfoForm").submit();
	
		}
	
	
	
	
	
	
		/*if($("#memberTell").val() == ""){
			$(".tellChk").text("※ 휴대폰은 필수입력입니다");
			$(".tellChk").css("color", "red");
		} 
		if($("#memberEmail").val() == ""){
			$(".emailChk").text("※ 이메일은 필수입력입니다");
			$(".emailChk").css("color", "red");
		}*/
		
		
	/*else{
		$("#myInfoForm").submit();
	}*/
	
	
	/*if($("#memberTell").val() == ""||$("#memberEmail").val() == ""){
		if($("#memberTell").val() == ""){
			$(".tellChk").text("※ 휴대폰은 필수입력입니다");
			$(".tellChk").css("color", "red");
		} 
		if($("#memberEmail").val() == ""){
			$(".emailChk").text("※ 이메일은 필수입력입니다");
			$(".emailChk").css("color", "red");
		}
		
		
	}else{
		$("#myInfoForm").submit();
	}*/
	
	
}
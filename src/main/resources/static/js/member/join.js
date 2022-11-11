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
				alert('실패');
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


function test(){
	Swal.fire({
	   title: '정말로 그렇게 하시겠습니까?',
	   text: '다시 되돌릴 수 없습니다. 신중하세요.',
	   icon: 'error',
	   
	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
	   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
	   confirmButtonText: '승인', // confirm 버튼 텍스트 지정
	   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
	   
	   reverseButtons: false, // 버튼 순서 거꾸로
   
}).then(result => {
   // 만약 Promise리턴을 받으면,
   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
   
      Swal.fire('승인이 완료되었습니다.', '화끈하시네요~!', 'success');
   }
});

}


		
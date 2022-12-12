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
	if(member_id == "" || member_id.length < 3){
			$(".successNameChk").text("아이디는 3자 이상 8자 이하로 설정해주세요 :)");
			$(".successNameChk").css("color", "red");
			
			document.querySelector('#duplicate_check').dataset.isClick = '1';
			setJoinBtn();
			
			if(member_id !== ""){
				document.querySelector('#duplicate_check').dataset.isClick = '1';
				setJoinBtn();
			}
	}else{
	
		//ajax start
		$.ajax({
			url: '/member/checkDuplIdAjax', //요청경로
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
				
				document.querySelector('#duplicate_check').dataset.isClick = '1';
				setJoinBtn();
				
			},
			error: function() {
				alert('실패');
			}
		});
		//ajax end
	}
}


function check1(){
	document.querySelector('#checkBtn').dataset.isClick = '1';
	setJoinBtn();
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

//휴대폰 인증번호 전송버튼 클릭
$('#sendPhoneNumber').click(function(){
    let memberTell = $('#memberTell').val();
    if(memberTell != ''){
		 Swal.fire(
                    'ok',
                    '인증번호 발송이 완료되었습니다.',
                    'success'
                    )
	}else{
		 Swal.fire(
                    'error',
                    '휴대폰 번호를 입력해주세요.',
                    'error'
                    )
	}

    $.ajax({
        type: "post",
        url: "/sms/sendSMSAjax",
        data: {"memberTell" : memberTell},
        success: function(res){
            $('#checkBtn').click(function test(){
               if($.trim(res) ==$('#inputCertifiedNumber').val()){
                    Swal.fire(
                        '인증성공!',
                        '휴대폰 인증이 정상적으로 완료되었습니다.',
                        'success'
                    )
  
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: '인증오류',
                        text: '인증번호가 올바르지 않습니다!',
                    })
                }
            })

        }
    })
});

//버튼을 활성화, 비활성화하는 함수   
function setJoinBtn(){
	const c1 = document.querySelector('#duplicate_check').dataset.isClick;
	const c2 = document.querySelector('#checkBtn').dataset.isClick;
	
	if(c1 == 1 && c2 == 1){
		//회원가입 버튼 활성화
		document.querySelector('#joinBtn1').disabled = false;
	}
}


		
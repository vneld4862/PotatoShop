
function searchAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	const roadAddr = data.roadAddress; 
        	document.querySelector('#memberAddr').value = roadAddr;
        }
    }).open();
}

function fn_idChk(){
	
	//ajax start
	$.ajax({
		url: '/member/idChk', //요청경로
		type: 'post',
		//data: {'memberId':member_id} //필요한 데이터
		data : {"userId" : $("#userId").val()},
		dataType : "json",
		success : function(data){
			if(data == 1){
				alert("중복된 아이디입니다.");
			}
			else if(data == 0){
				$("#idChk").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
				}
			}
	});
	}//ajax end


		
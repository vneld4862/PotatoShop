
function searchId(){
	const name = $('#memberName').val();
	let phone = $('#memberTell').val();
	
	//연락처에서 모든 '-'를 ''로 변경
	phone = phone.replace(/-/gi, "");

	//ajax start
	$.ajax({
	    url: '/member/doSearchId', //요청경로
	    type: 'post',
	    data:{"memberName":name, "memberTell":phone}, //필요한 데이터
	    success: function(result) {
			$('#searchId_result').empty();

			let str = '';

			if(result == ''){
				str += '<span>Id를 찾지 못했습니다.</span>';
			}
			else{
				str += `<span>${result}</span>`;
			}
			$('#searchId_result').append(str);
			
			//모달창 띄우기
			const modal = new bootstrap.Modal('#searchIdModal');
			modal.show();
	      
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	//ajax end

}
 

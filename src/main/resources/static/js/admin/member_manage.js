//회원 리스트 행 클릭하면 실행
function getMemberDetail(memberId){
	//ajax start
	$.ajax({
		url: '/admin/selectMemberDetail', //요청경로
		type: 'post',
		data: {'memberId':memberId}, //필요한 데이터
		success: function(result) {
			alert('aaa');
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}
//회원 리스트 행 클릭하면 실행
function getMemberDetail(memberId){
	//ajax start
	$.ajax({
		url: '/admin/selectMemberDetail', //요청경로
		type: 'post',
		data: {'memberId':memberId}, //필요한 데이터
		success: function(result) {
			
			let str = '';
			str += '<div class="row">                                                      '
			str += '		<div class="mb-3">                                                '
			str += '			<회원 상세 정보>                                              '
			str += '		</div>                                                            '
			str += '		<table class="table table-striped text-center">                   '
			str += '			<colgroup>'
			str += '				<col width="30%">'
			str += '				<col width="70%">'
			str += '			</colgroup>	'
			str += '			<tr>                                                          '
			str += '				<td>이름</td>                                             '
			str += '				<td>주소</td>                                             '
			str += '			</tr>                                                         '
			str += `			<tr>`
			str += `				<td>${result.memberName}</td>`
			str += `				<td>${result.memberAddr} ${result.addrDetail}</td>`
			str += '			</tr>                                                         '
			str += '			<tr>                                                          '
			str += '				<td>연락처</td>                                           '
			str += '				<td>이메일</td>                                           '
			str += '			</tr>                                                         '
			str += `			<tr>                         `
			str += `				<td>${result.memberTell}</td>                  `
			str += `				<td>${result.memberEmail}</td>                 `
			str += '			</tr>                                                         '
			str += '		</table>                                                          '
			str += '</div>                                                                 '
			
			
			detailDiv.innerHTML = '';
			document.querySelector('#detailDiv').insertAdjacentHTML('beforeend', str);

			
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}
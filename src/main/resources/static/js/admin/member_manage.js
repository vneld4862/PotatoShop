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
	}); //ajax end
	
}

//회원 상태별 조회할 때
function changeStatus(){
	//memberStatus 값
	const memberStatus = document.querySelector('#statusSelectBox').value;
	
	//ajax start
	$.ajax({
		url: '/admin/memberListAjax', //요청경로
		type: 'post',
		data: {'memberStatus':memberStatus}, //필요한 데이터
		success: function(result) {
			//컨트롤러 내용 진행 후 자동 실행 구문
			
			//테이블을 다시 그려 준다
			const memberListTable = document.querySelector('.memberListTable');
			const tbody = memberListTable.querySelector('tbody');
			
			//부모 테이블 memberListTable의 자식 테이블 tbody만 삭제하겠다
			memberListTable.removeChild(tbody);
			
			//추가할 태그 생성
			let str = '';
			str += '<tbody>';
			for(const member, status of result) {
				str += '<tr>';
				str += '	<td></td>';
				str += '	<td>';
				str += `<span onclick="getMemberDetail('${member.memberId}')">${member.memberNickName}(${member.memberId})</td>`;
				str += '	<td>등급</td>';
				str += `	<td>${member.regDate}</td>`;
				str += '	<td>';
				str += '		<div class="form-check form-check-inline">';
				str += '			<input class="form-check-input" type="radio"';
				str += `				name="test_${status.count}" id="" value=""`;
				str += `				checked="${member.memberStatus == 'ACTIVE'}"`;
				str += `				onclick="changeMemberStatus('${member.memberId}', 'ACTIVE');">`;
				str += '			<label class="form-check-label" for="">활동 중</label>';
				str += '		</div>';
				str += '		<div class="form-check form-check-inline">';
				str += '			<input class="form-check-input" type="radio"';
				str += `				name="test_${status.count}" id="" value=""`;
				str += `				checked="${member.memberStatus == 'DELETED'}"`;
				str += `				onclick="changeMemberStatus('${member.memberId}', 'DELETED');">`;
				str += '			<label class="form-check-label" for="">탈퇴</label>';
				str += '		</div>';
				str += '	</td>';
				str += '</tr>';
			
			}
			str += '</tbody>';
			
			memberListTable.insertAdjacentHTML('beforeend', str);


			
		},
		error: function() {
			alert('실패');
		}
	}); //ajax end

}

//회원 상태 변경
function changeMemberStatus(memberId, memberStatus){
	const result = confirm('회원 상태를 변경하시겠습니까?');
	
	if(result){
		//ajax start
		$.ajax({
			url: '/admin/changeStatusAjax', //요청경로
			type: 'post',
			data: {'memberId':memberId, 'memberStatus':memberStatus}, //필요한 데이터
			success: function(result) {
				alert('회원 상태 변경이 완료되었습니다.');
				changeStatus();
			},
			error: function() {
				alert('실패');
			}
		});
		//ajax end
	}
}


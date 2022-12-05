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
			str += '	<div class="mb-3">                                                '
			str += '		<회원 상세 정보>                                              '
			str += '	</div>                                                            '
			str += '	<table class="table table-striped text-center">                   '
			str += '		<colgroup>'
			str += '			<col width="30%">'
			str += '			<col width="70%">'
			str += '		</colgroup>	'
			str += '		<tr>                                                          '
			str += '			<td>이름</td>                                             '
			str += '			<td>주소</td>                                             '
			str += '		</tr>                                                         '
			str += `		<tr>`
			str += `			<td>${result.memberName}</td>`
			str += `			<td>${result.memberAddr} ${result.addrDetail}</td>`
			str += '		</tr>                                                         '
			str += '		<tr>                                                          '
			str += '			<td>연락처</td>                                           '
			str += '			<td>이메일</td>                                           '
			str += '		</tr>                                                         '
			str += `		<tr>                         `
			str += `			<td>${result.memberTell}</td>                  `
			str += `			<td>${result.memberEmail}</td>                 `
			str += '		</tr>                                                         '
			str += '	</table>                                                          '
			str += '</div>                                                                 '
			
			detailDiv.innerHTML = '';
			document.querySelector('#detailDiv').insertAdjacentHTML('beforeend', str);
		},
		error: function() {
			alert('실패');
		}
	}); //ajax end			
			
	//ajax start
	$.ajax({
		url: '/admin/selectWrittenReview', //요청경로
		type: 'post',
		data: {'buyer':memberId}, //필요한 데이터
		success: function(result) {

			let str = '';
			
			str += '<div class="row">';
			str += '	<div class="mb-3">';
			str += '		<다른 상점에 남긴 후기>';
			str += '	</div>';

			str += '	<div class="col">';
			str += '		<table class="table text-center">';
			str += '			<colgroup>';
			str += '				<col width="10%">';
			str += '				<col width="*">';
			str += '				<col width="30%">';
			str += '			</colgroup>';
			str += '			<thead>';
			str += '				<tr>';
			str += '					<th scope="col">No</th>';
			str += '					<th scope="col">제목</th>';
			str += '					<th scope="col">작성일</th>';
			str += '				</tr>';
			str += '			</thead>';
			str += '			<tbody>';
			
			if(result.length == 0){
				str += '<tr><td colspan=3>작성한 리뷰가 없습니다.</td></tr>'
			}
			
			for(const review of result) {
				str += '				<tr>';
				str += '					<td>1</td>';
				str += `					<td onclick="reviewDetail('${review.itemCode}');">${review.boardTitle}</td>`;
				str += `					<td>${review.regDate}</td>`;
				str += '				</tr>';
			}
			
			str += '			</tbody>';
			str += '		</table>';
			str += '	</div>';
			str += '</div>';
			
			reviewDiv.innerHTML = '';
			document.querySelector("#reviewDiv").insertAdjacentHTML('beforeend', str);

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
			if(result.length == 0) {
				
				str += '<tr>';
				str += '	<td colspan="4">회원이 없습니다.</td>';
				str += '<tr>';
				
			} else {
				//for(const member of result) {
				for(let i = 0 ; i < result.length ; i++) {
					str += '<tr>';
					str += `	<td>${i+1}</td>`;
					str += '	<td>';
					str += `<span onclick="getMemberDetail('${result[i].memberId}')">${result[i].memberNickName}(${result[i].memberId})</td>`;
					str += `	<td>${result[i].regDate}</td>`;
					str += '	<td>';
					str += '		<div class="form-check form-check-inline">';
					str += '			<input class="form-check-input" type="radio"';
					str += `				name="test_${i}" id="" value=""`;
					if(result[i].memberStatus == 'ACTIVE'){
						str += `				checked`;
					}		
					str += `				onclick="changeMemberStatus('${result[i].memberId}', 'ACTIVE');">`;
					str += '			<label class="form-check-label" for="">활동 중</label>';
					str += '		</div>';
					str += '		<div class="form-check form-check-inline">';
					str += '			<input class="form-check-input" type="radio"';
					str += `				name="test_${i}" id="" value=""`;
					if(result[i].memberStatus == 'DELETED'){
						str += `				checked`;
					}
					str += `				onclick="changeMemberStatus('${result[i].memberId}', 'DELETED');">`;
					str += '			<label class="form-check-label" for="">탈퇴</label>';
					str += '		</div>';
					str += '	</td>';
					str += '</tr>';
				
				}
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




//모달 창 선택
const reviewDetailModal = new bootstrap.Modal('#reviewDetailModal');

//리뷰 제목 클릭하면 실행
function reviewDetail(itemCode){
	
//	const loginId = document.querySelector('#hiddenId').value;
	
	//제목 클릭과 동시에 댓글 폼에 있는 itemCode에 값 넣어 주기
//	const hiddenItemCode = document.querySelector('#hiddenItemCode').value;
//	document.querySelector('#replyItemCode').value = hiddenItemCode;
	
	//ajax start
	$.ajax({
		url: '/board/reviewDetail', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
		success: function(result) {
			
			//리뷰 상세보기
			document.querySelector('.detailAjaxDiv').innerHTML = '';
			
			let str = '';

			str += '<div class="row">';
			str += '	<div class="col-3">';
			str += `		<img src="/images/${result.itemVO.imgList[0].attachedName}" style="width: 100px;">`;
			str += '	</div>';
			str += '	<div class="col-9 text-start">';
			str += '		<div class="col modalItemName">';
			str += `			${result.itemVO.itemName}`
			str += '		</div>';
			str += '		<div class="col">';
			str += '			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">'
			str += '				<path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>'
			str += '			</svg>'
			str += `			${result.starPoint}`
			str += '		</div>';
			str += '	</div>';
			str += '</div>';
	        
			str += '<hr>';
				
			str += `<div class="col text-end">${result.regDate}</div>`;
			str += '<div class="col modalBoardTitle">'
			str += `	${result.boardTitle}`
			str += '</div>';
				
			str += '<hr>';
			
			//사진 있는 후기만 사진 조회
			if(result.reviewImgVO.savedName != null){
				str += '	<div class="col mb-4">';
				str += '		<div style="font-size:0.8rem; color:#B2B2B2;" class="mb-4">'
				str += '			<span>사진 클릭 시 새로운 탭에서 원본 사이즈로 볼 수 있습니다.</span>'
				str += '		</div>'
				str += `		<img src="/images/${result.reviewImgVO.savedName}" style="width: 200px;" onclick="window.open(this.src)">`;
				str += '	</div>';
				str += '	<div class="col modalBoardContent">'
				str += `		${result.boardContent}`
				str += '	</div>';
				str += '</th:block>';
			}
			else{
				str += '	<div class="col modalBoardContent">'
				str += `		${result.boardContent}`
				str += '	</div>';
			}
			
			str += '<hr>';
			
			document.querySelector('.detailAjaxDiv').innerHTML = str;
			
			reviewDetailModal.show();
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}

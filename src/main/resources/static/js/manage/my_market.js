//모달 창 선택
const reviewDetailModal = new bootstrap.Modal('#reviewDetailModal');

//리뷰 제목 클릭하면 실행
function reviewDetail(itemCode){
	
	const loginId = document.querySelector('#hiddenId').value;
	
	//제목 클릭과 동시에 댓글 폼에 있는 itemCode에 값 넣어 주기
	const hiddenItemCode = document.querySelector('#hiddenItemCode').value;
	document.querySelector('#replyItemCode').value = hiddenItemCode;
	
	//ajax start
	$.ajax({
		url: '/board/reviewDetail', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
		success: function(result) {
			
			//리뷰 상세보기
			document.querySelector('.detailAjaxDiv').innerHTML = '';
			
			let str = '';
			
			if(result.memberId = loginId) { //글 쓴 사람과 로그인한 사람이 일치하면
				str += '<div class="col-12 text-end mb-3">'
				str += '	<span data-bs-toggle="modal" data-bs-target="#review_update_modal">수정</span>|'
				str += `	<span onclick="deleteReviewAjax('${result.itemVO.itemCode}')">삭제</span>`
				str += '</div>'	
			}
			else { //일치하지 않으면

			}
			
			
			str += '<div class="row">';
			str += '	<div class="col-3">';
			str += `		<img src="/images/${result.itemVO.imgList[0].attachedName}" style="width: 100px;">`;
			str += '	</div>';
			str += '	<div class="col-9 text-start">';
			str += '		<div class="col">';
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
			str += `<div class="col">${result.boardTitle}</div>`;
				
			str += '<hr>';
			
			//사진 있는 후기만 사진 조회
			if(result.reviewImgVO.savedName != null){
				str += '	<div class="col">';
				str += `		<img src="/images/${result.reviewImgVO.savedName}" style="width: 200px;">`;
				str += '	</div>';
				str += `	<div class="col">${result.boardContent}</div>`;
				str += '</th:block>';
			}
			else{
				str += `	<div class="col">${result.boardContent}</div>`;
			}
			
			str += '<hr>';
			
			document.querySelector('.detailAjaxDiv').innerHTML = str;
			selectReviewReply(`${result.boardNum}`);
			
			reviewDetailModal.show();
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}


//리뷰 댓글 조회
function selectReviewReply(boardNum){
	
	//ajax start
	$.ajax({
		url: '/board/selectReviewReply', //요청경로
		type: 'post',
		data: {'boardNum':boardNum}, //필요한 데이터
		success: function(result) {
			
			document.querySelector('.replyListDiv').innerHTML = '';
			
			let str = '';
			
			for(const reply of result){
				str += '<div class="row">';
				str += '<div class="col-12 text-end">';
				str += `	<span style="font-size: 0.8rem;">${reply.replyRegDate}</span>`;
				str += '</div>';
				str += `<div class="col-12 text-start">${reply.memberId}</div>`;
				str += '<div class="col-12">';
				str += `	${reply.replyContent}`;
				str += '</div>';
				str += '<div class="col-12 text-end mb-3">';
				str += `	<input type="button" value="삭제" class="btn btn-secondary" onclick="deleteReplyAjax('${reply.replyNum}')">`;
				str += '</div>';
				str += '<hr>';		
				str += '</div>';
			}
		
			
			document.querySelector('.replyListDiv').innerHTML = str;
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end	
	
}


//댓글 등록 버튼 시 실행되는 함수
function regReply(){
	const itemCode = document.querySelector('#replyItemCode').value;
	const replyContent = document.querySelector('#replyContent').value;
	
	//ajax start
	$.ajax({
		url: '/board/regReplyAjax', //요청경로
		type: 'post',
		data: {'itemCode':itemCode
				, 'replyContent':replyContent}, //필요한 데이터
		success: function(result) {
			let str = '';
			
			str += '<div class="row">';
			str += '<div class="col-12 text-end">';
			str += `	<span style="font-size: 0.8rem;">${result.replyRegDate}</span>`;
			str += '</div>';
			str += `<div class="col-12 text-start">${result.memberId}</div>`;
			str += '<div class="col-12">';
			str += `	${result.replyContent}`;
			str += '</div>';
			str += '<div class="col-12 text-end mb-3">';
			str += `	<input type="button" value="삭제" class="btn btn-secondary" onclick="deleteReplyAjax('${result.replyNum}')">`;
			str += '</div>';
			str += '<hr>';		
			str += '</div>';
			
		
			// $('.replyListDiv > div:nth-child(1)').before(str);	
			//$('.replyListDiv').prepend(str);
			document.querySelector('.replyListDiv').insertAdjacentHTML('afterbegin', str);
			document.querySelector('#replyContent').value = '';
			
		},
		error: function() {
			alert('실패');
		}
	});
	
}


//리뷰 삭제 클릭시 실행되는 Ajax
function deleteReviewAjax(itemCode){
	
	//ajax start
	$.ajax({
		url: '/board/deleteReview', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터 를 가지고 컨트롤러로 가서 성공하면 밑에 석세스문이 실행
		success: function(result) {
			alert('리뷰가 삭제되었습니다.');
			location.href="/manage/myMarket"
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
	
}

//리뷰 댓글 삭제 클릭시 실행되는 Ajax
function deleteReplyAjax(replyNum){
	
	const result = confirm('댓글을 삭제하시겠습니까?', replyNum);
	
	if(result) {
		//ajax start
		$.ajax({
			url: '/board/deleteReply', //요청경로
			type: 'post',
			data: {'replyNum':replyNum}, //필요한 데이터 를 가지고 컨트롤러로 가서 성공하면 밑에 석세스문이 실행
			success: function(result) {
				alert('댓글이 삭제되었습니다.');
				location.href="/manage/myMarket"
			},
			error: function() {
				alert('실패');
			}
		});
		//ajax end
	}
	
}



//리뷰 수정 클릭시 실행되는 Ajax
//나중에 시간 남으면.................
function updateReviewAjax(itemCode){


	//ajax start
	$.ajax({
		url: '/board/updateReview', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
		success: function(result) {

		},
		error: function() {
			alert('실패');
		}
	});

}
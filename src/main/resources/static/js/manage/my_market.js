//모달 창 선택
const reviewDetailModal = new bootstrap.Modal('#reviewDetailModal');

//리뷰 제목 클릭하면 실행
function reviewDetail(itemCode){
	//ajax start
	$.ajax({
		url: '/board/reviewDetail', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
		success: function(result) {
			
			//document.querySelector('#reviewDetailModal_itemName').innerText = result.itemVO.itemName;
			//document.querySelector('#reviewDetailModal_starPoint').innerText = result.starPoint;
			//document.querySelector('#reviewDetailModal_title').innerText = result.boardTitle;
			//document.querySelector('#reviewDetailModal_wirter').innerText = result.memberId;
			//document.querySelector('#reviewDetailModal_pic').innerText = 
			
			
			
			document.querySelector('.detailAjaxDiv').innerHTML = '';
			
			let str = '';
			
			str += '<div class="col-12 text-end mb-3">'
			str += '	<span>수정</span>|'
			str += `	<span onclick="deleteReviewAjax('${result.itemVO.itemCode}')">삭제</span>`
			str += '</div>'			
			
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
			
			reviewDetailModal.show();
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}


//리뷰 삭제 클릭시 실행되는 Ajax
function deleteReviewAjax(itemCode){	
	//ajax start
	$.ajax({
		url: '/board/deleteReview', //요청경로
		type: 'post',
		data: {'itemCode':itemCode}, //필요한 데이터
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
	
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
//			document.querySelector('#reviewDetailModal_itemName').innerText = result.itemVO.itemName;
//			document.querySelector('#reviewDetailModal_starPoint').innerText = result.starPoint;
//			document.querySelector('#reviewDetailModal_title').innerText = result.boardTitle;
//			document.querySelector('#reviewDetailModal_wirter').innerText = result.memberId;
//			document.querySelector('#reviewDetailModal_pic').innerText = result;
			
			//const reviewDetailDiv = document.querySelector('.reviewDetail');
			//const detailAjaxDiv = document.querySelector('.detailAjaxDiv');
			//reviewDetailDiv.removeChild(detailAjaxDiv);
			
			document.querySelector('.detailAjaxDiv').innerHTML = '';
			
			let str = '';
			
			str += '	<div class="row">';
			str += '		<div class="col-4">상품 메인 사진</div>';
			str += '		<div class="col-8">';
			str += '			<div class="row">';
			str += `				${result.itemVO.itemName}`
			str += '			</div>';
			str += '			<div class="row">';
			str += '				<div class="col">별점</div>';
			str += '				<div class="col">';
			str += `					${result.starPoint}`
			str += '				</div>';
			str += '			</div>';
			str += '		</div>';
			str += '	</div>';
	
			str += '	<hr>';
				
			str += `	<div class="col">${result.boardTitle}</div>`;
			str += `	<div class="col">${result.boardContent}</div>`;
				
			str += '	<hr>';
				
			str += '	<div class="col">';
			str += `		<img src="/images/${result.reviewImgVO.attachedName}">`;
			str += '	</div>';
			str += '	<div class="col">내용2</div>';
			str += '	<div class="col text-end">등록일2</div>';
				
			str += '	<hr>';
				
			
			document.querySelector('.detailAjaxDiv').innerHTML = str;
			
			reviewDetailModal.show();
			
		},
		error: function() {
			alert('실패');
		}
	});
//ajax end
}
function mainCate(selectBtn)
{
	const mainCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	$.ajax({
		url: '/admin/mainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateName':mainCateName}, //필요한 데이터
		success: function(result) {
			alert('등록되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
}
function subCate(selectBtn)
{
	const mainCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	$.ajax({
		url: '/admin/mainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateName':mainCateName}, //필요한 데이터
		success: function(result) {
			alert('등록되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
}
function detailCate(selectBtn)
{
	const mainCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	$.ajax({
		url: '/admin/mainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateName':mainCateName}, //필요한 데이터
		success: function(result) {
			alert('등록되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
}

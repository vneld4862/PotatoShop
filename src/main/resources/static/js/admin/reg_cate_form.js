function mainCate(selectBtn)
{
	const mainCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	/*alert(selectBtn.dataset.main)*/
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
//선택한 메인카테고리의 서브 카테고리 조회
const subCateList=document.querySelector('.subCateList')
const subCateSelect=document.querySelector('#subCateSelect');
const mainCateList=subCateSelect.querySelectorAll('.mainCateCodeList');
subCateSelect.addEventListener('change',function(){
	//alert(subCateSelect.dataset.mainCateCode);
	for(const i of mainCateList)
	{
		if(subCateSelect.value==i.innerText)
		{
			subCateSelect.dataset.mainCateCode=i.dataset.mainCateCode;
			insertMainCateCode=subCateSelect.dataset.mainCateCode;
		}
	}
	alert(insertMainCateCode);
	subCateList.querySelector('option').innerText='';
	let str='';
	/*$.ajax({
		url: '/admin/subCateAjax', //요청경로
		type: 'post',
		data: {'mainCateCode':insertMainCateCode
				,'subCateName':subCateName}, //필요한 데이터
		success: function(result) {
			alert('등록되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});*/
})

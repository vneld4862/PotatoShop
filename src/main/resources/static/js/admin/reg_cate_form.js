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
	const subCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	const mainCateCode=selectBtn.closest('form').querySelector('#subCateSelect').value;
	if(mainCateCode=='not')
	{
		alert('카테고리를 골라주세요.')
		return
	}
	$.ajax({
		url: '/admin/subCateAjax', //요청경로
		type: 'post',
		data: {'mainCateCode':mainCateCode
				,'subCateName':subCateName}, //필요한 데이터
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
	const detailCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	const subCateCode=document.querySelector('#detailCateSelect').value;
	$.ajax({
		url: '/admin/detailCateAjax', //요청경로
		type: 'post',
		data: {'detailCateName':detailCateName
				,'subCateCode':subCateCode}, //필요한 데이터
		success: function(result) {
			alert('등록되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
}

//선택한 메인카테고리의 서브 카테고리 조회
const subCateSelect=document.querySelector('#subCateSelect');
const detailCateSelect=document.querySelector('#detailCateSelect');
subCateSelect.addEventListener('change',function(){
	const mainCateCode=subCateSelect.value;
	if(mainCateCode=='not')
	{
		const chooseStr='<option value="not">선택</option>';
		detailCateSelect.innerHTML='';
		detailCateSelect.insertAdjacentHTML('afterbegin',chooseStr);
		return
	}

	$.ajax({
		url: '/admin/selectSubCateAjax', //요청경로
		type: 'post',
		data: {'mainCateCode':mainCateCode}, //필요한 데이터
		success: function(result) {
			detailCateSelect.innerHTML='';
			let str='';
			for(const subCate of result)
			{
				if(subCate.subCateName=='')
				{
					alert('비었슴');
				}
				if(subCate.subCateName==null)
				{
					alert('null임')
				}
				str+=`<option value="${subCate.subCateCode}">${subCate.subCateName}</option>`;
			}
			detailCateSelect.insertAdjacentHTML('afterbegin',str);
		},
		error: function() {
			alert('실패');
		}
	});

})

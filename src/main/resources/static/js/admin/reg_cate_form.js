function mainCate(selectBtn)
{
	const mainCateName=selectBtn.closest('form').querySelector('.inputCateName').value;
	const mainCountList=document.querySelectorAll('.mainCountList');
	if(mainCountList[mainCountList.length-1]==null)
	{
		let mainCount=parseInt(mainCountList[mainCountList.length-1].innerText);
	}
	else
	{
		let mainCount=1;		
	}
	
	
	const mainCateList=document.querySelector('#mainCateList');
	$.ajax({
		url: '/admin/mainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateName':mainCateName}, //필요한 데이터
		success: function(result) {
			const nextMainCateCode=result;
			
			let str='<tr>';
			str+='<td><input class="form-check-input" type="checkbox" value=""></td>';
			str+=`<td class="text-center mainCountList">${mainCount+1}</td>`;
			str+=`<td>${nextMainCateCode}</td>`;
			str+=`<td>${mainCateName}</td>`;
			str+=`<td>USE</td>`;
			str+='</tr>';
			mainCateList.insertAdjacentHTML('beforeend',str);
			mainCount+=1;
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
	const subCountList=document.querySelectorAll('.subCountList');
	let subCount=parseInt(subCountList[subCountList.length-1].innerText);
	const subCateList=document.querySelector('#subCateList');
	const subSelectBox=selectBtn.closest('form').querySelector('#subCateSelect');
	
	const selectMainName=subSelectBox.options[subSelectBox.selectedIndex].text;
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
			const nextSubCateCode=result;
			
			let str='<tr>';
			str+='<td><input class="form-check-input" type="checkbox" value=""></td>';
			str+=`<td class="text-center subCountList">${subCount+1}</td>`;
			str+=`<td>${nextSubCateCode}</td>`;
			str+=`<td>${subCateName}</td>`;
			str+=`<td>${selectMainName}</td>`;
			str+='</tr>';
			subCateList.insertAdjacentHTML('beforeend',str);
			subCount+=1;
			
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
	const detailCateList=document.querySelector('#detailCateList');
	const detailCountList=document.querySelectorAll('.detailCountList');
	let detailCount=parseInt(detailCountList[detailCountList.length-1].innerText);
	const detailSelectBox=selectBtn.closest('form').querySelector('#detailCateSelect');
	
	const selectSubName=detailSelectBox.options[detailSelectBox.selectedIndex].text;
	if(subCateCode=='not')
	{
		alert('카테고리를 골라주세요.')
		return
	}
	$.ajax({
		url: '/admin/detailCateAjax', //요청경로
		type: 'post',
		data: {'detailCateName':detailCateName
				,'subCateCode':subCateCode}, //필요한 데이터
		success: function(result) {
			
			const nextDetailCateCode=result;
			
			let str='<tr>';
			str+='<td><input class="form-check-input" type="checkbox" value=""></td>';
			str+=`<td class="text-center subCountList">${detailCount+1}</td>`;
			str+=`<td>${nextDetailCateCode}</td>`;
			str+=`<td>${detailCateName}</td>`;
			str+=`<td>${selectSubName}</td>`;
			str+='</tr>';
			detailCateList.insertAdjacentHTML('beforeend',str);
			detailCount+=1;
			
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

//전체 체크박스 선택

function mainCate(selectBtn)
{
	const inputMainCate=selectBtn.closest('.form').querySelector('.inputCateName');
	const mainCateName=inputMainCate.value;
	const mainCountList=document.querySelectorAll('.mainCountList');
	let mainCount=parseInt(mainCountList[mainCountList.length-1].innerText);
	const mainCateList=document.querySelector('#mainCateList');
	const textChk=mainCateList.querySelector('.noneText');
	if(mainCateName=='')
	{
		alert('입력이 잘못되었습니다.\n다시입력해주십시오.')
		return
	}
	if(textChk.innerText=='등록된 카테고리가 없습니다.')
	{
		mainCateList.innerText='';
		mainCount=0;
	}
	
	$.ajax({
		url: '/admin/mainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateName':mainCateName}, //필요한 데이터
		success: function(result) {
			const nextMainCateCode=result;
			
			let str='<tr>';
			str+=`<td><input class="form-check-input mainChk" name="mainCbox" type="checkbox" value="${nextMainCateCode}"></td>`;
			str+=`<td class="text-center mainCountList">${mainCount+1}</td>`;
			str+=`<td>${nextMainCateCode}</td>`;
			str+=`<td class="noneText">${mainCateName}</td>`;
			str+=`<td>USE</td>`;
			str+='</tr>';
			mainCateList.insertAdjacentHTML('beforeend',str);
			mainCount+=1;
			inputMainCate.value='';
			alert('등록되었습니다.');
			let op='';
			op+=`<option class="mainCateCodeList" value="${nextMainCateCode}">${mainCateName}</option>`;
			subCateSelect.insertAdjacentHTML("beforeend",op);
		},
		error: function() {
			alert('실패');
		}
	});
}
function subCate(selectBtn)
{
	const inputSubCate=selectBtn.closest('.form').querySelector('.inputCateName');
	const subCateName=inputSubCate.value;
	const subSelectBox=selectBtn.closest('.form').querySelector('#subCateSelect');
	const mainCateCode=subSelectBox.value;
	const subCountList=document.querySelectorAll('.subCountList');
	let subCount=parseInt(subCountList[subCountList.length-1].innerText);
	const subCateList=document.querySelector('#subCateList');
	const textChk=subCateList.querySelector('.noneText');
	
	const selectMainName=subSelectBox.options[subSelectBox.selectedIndex].text;
	if(mainCateCode=='not')
	{
		alert('선택된 카테고리가 없습니다.')
		return
	}
	if(subCateName=='')
	{
		alert('입력이 잘못되었습니다.\n다시입력해주십시오.')
		return
	}
	if(textChk.innerText=='등록된 카테고리가 없습니다.')
	{
		subCateList.innerText='';
		subCount=0;
	}
	$.ajax({
		url: '/admin/subCateAjax', //요청경로
		type: 'post',
		data: {'mainCateCode':mainCateCode
				,'subCateName':subCateName}, //필요한 데이터
		success: function(result) {
			const nextSubCateCode=result;
			
			let str='<tr>';
			str+=`<td><input class="form-check-input subChk" type="checkbox" name="subCbox" value="${nextSubCateCode}"></td>`;
			str+=`<td class="text-center subCountList">${subCount+1}</td>`;
			str+=`<td>${nextSubCateCode}</td>`;
			str+=`<td class="noneText">${subCateName}</td>`;
			str+=`<td>${selectMainName}</td>`;
			str+='</tr>';
			subCateList.insertAdjacentHTML('beforeend',str);
			subCount+=1;
			
			alert('등록되었습니다.');
			inputSubCate.value='';
			
		},
		error: function() {
			alert('실패');
		}
	});
}
function detailCate(selectBtn)
{
	const inputDetailCate=selectBtn.closest('.form').querySelector('.inputCateName');
	const detailCateName=inputDetailCate.value;
	const detailCateList=document.querySelector('#detailCateList');
	const subCateCode=document.querySelector('#detailCateSelect').value;
	const textChk=detailCateList.querySelector('.noneText');
	const detailCountList=document.querySelectorAll('.detailCountList');
	let detailCount=parseInt(detailCountList[detailCountList.length-1].innerText);
	const detailSelectBox=selectBtn.closest('.form').querySelector('#detailCateSelect');
	
	const selectSubName=detailSelectBox.options[detailSelectBox.selectedIndex].text;
	if(subCateCode=='not')
	{
		alert('선택된 카테고리가 없습니다.')
		return
	}
	if(detailCateName=='')
	{
		alert('입력이 잘못되었습니다.\n다시입력해주십시오.')
		inputDetailCate.value='';
		return
	}
	if(textChk.innerText=='등록된 카테고리가 없습니다.')
	{
		detailCateList.innerText='';
		detailCount=0;
	}
	$.ajax({
		url: '/admin/detailCateAjax', //요청경로
		type: 'post',
		data: {'detailCateName':detailCateName
				,'subCateCode':subCateCode}, //필요한 데이터
		success: function(result) {
			
			const nextDetailCateCode=result;
			
			let str='<tr>';
			str+=`<td><input class="form-check-input detailChk" type="checkbox" name="detailCbox" value="${nextDetailCateCode}"></td>`;
			str+=`<td class="text-center detailCountList">${detailCount+1}</td>`;
			str+=`<td class="noneText">${nextDetailCateCode}</td>`;
			str+=`<td>${detailCateName}</td>`;
			str+=`<td>${selectSubName}</td>`;
			str+='</tr>';
			detailCateList.insertAdjacentHTML('beforeend',str);
			detailCount+=1;
			
			alert('등록되었습니다.');
			inputDetailCate.value='';
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
				str+='<option value="not">선택</option>';
				str+=`<option value="${subCate.subCateCode}">${subCate.subCateName}</option>`;
			}
			if(!result.length==0)
			{
				detailCateSelect.insertAdjacentHTML('afterbegin',str);
			}
			else
			{
				const nullStr='<option value="not">미등록</option>';
				detailCateSelect.insertAdjacentHTML('afterbegin',nullStr);
			}
		},
		error: function() {
			alert('실패');
		}
	});

})

//전체 체크박스 선택


function mainCheckAll() {
	if($("#mainCbox").is(':checked')) {
		$("input[name=mainCbox]").prop("checked", true);
	} else {
		$("input[name=mainCbox]").prop("checked", false);
	}
}

$(document).on("click", "input:checkbox[name=mainCbox]", function(e) {
	
	var chks = document.getElementsByName("mainCbox");
	var chksChecked = 0;
	
	for(var i=0; i<chks.length; i++) {
		var cbox = chks[i];
		
		if(cbox.checked) {
			chksChecked++;
		}
	}
	
	if(chks.length == chksChecked){
		$("#mainCbox").prop("checked", true);
	}else{
		$("#mainCbox").prop("checked",false);
	}
	
});

function subCheckAll() {
	if($("#subCbox").is(':checked')) {
		$("input[name=subCbox]").prop("checked", true);
	} else {
		$("input[name=subCbox]").prop("checked", false);
	}
}

$(document).on("click", "input:checkbox[name=subCbox]", function(e) {
	
	var chks = document.getElementsByName("subCbox");
	var chksChecked = 0;
	
	for(var i=0; i<chks.length; i++) {
		var cbox = chks[i];
		
		if(cbox.checked) {
			chksChecked++;
		}
	}
	
	if(chks.length == chksChecked){
		$("#subCbox").prop("checked", true);
	}else{
		$("#subCbox").prop("checked",false);
	}
	
});

function detailCheckAll() {
	if($("#detailCbox").is(':checked')) {
		$("input[name=detailCbox]").prop("checked", true);
	} else {
		$("input[name=detailCbox]").prop("checked", false);
	}
}

$(document).on("click", "input:checkbox[name=detailCbox]", function(e) {
	
	var chks = document.getElementsByName("detailCbox");
	var chksChecked = 0;
	
	for(var i=0; i<chks.length; i++) {
		var cbox = chks[i];
		
		if(cbox.checked) {
			chksChecked++;
		}
	}
	
	if(chks.length == chksChecked){
		$("#detailCbox").prop("checked", true);
	}else{
		$("#detailCbox").prop("checked",false);
	}
	
});

//선택 삭제

function mainDelete()
{
	const re=confirm('정말 삭제하시겠습니까?');
	if(re)
	{
	const checkedBoxes=document.querySelectorAll('.mainChk:checked');
	if(checkedBoxes.length==0)
	{
		alert('선택된 항목이 없습니다.')
		return
	}
	let mainCateCodes='';
	for(const checkedBox of checkedBoxes)
	{
		mainCateCodes+=checkedBox.value+',';
	}
	$.ajax({
		url: '/admin/deleteMainCateAjax', //요청경로
		type: 'post',
		data: {'mainCateCodes':mainCateCodes}, //필요한 데이터
		success: function(result) {
			alert('삭제되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
	setTimeout(function(){
		location.href='/admin/regCateForm';
	},20);
	}
}

function subDelete()
{
	const re=confirm('정말 삭제하시겠습니까?');
	if(re)
	{
	const checkedBoxes=document.querySelectorAll('.subChk:checked');
	if(checkedBoxes.length==0)
	{
		alert('선택된 항목이 없습니다.')
		return
	}
	let subCateCodes='';
	for(const checkedBox of checkedBoxes)
	{
		subCateCodes+=checkedBox.value+',';
	}
	$.ajax({
		url: '/admin/deleteSubCateAjax', //요청경로
		type: 'post',
		data: {'subCateCodes':subCateCodes}, //필요한 데이터
		success: function(result) {
			alert('삭제되었습니다.');
		},
		error: function() {
			alert('실패');
		}
	});
	setTimeout(function(){
		location.href='/admin/regCateForm';
	},20);
	}
}

function detailDelete()
{
	const re=confirm('정말 삭제하시겠습니까?');
	if(re)
	{
		const checkedBoxes=document.querySelectorAll('.detailChk:checked');
		if(checkedBoxes.length==0)
		{
			alert('선택된 항목이 없습니다.')
			return
		}
		let detailCateCodes='';
		for(const checkedBox of checkedBoxes)
		{
			detailCateCodes+=checkedBox.value+',';
		}
		$.ajax({
			url: '/admin/deleteDetailCateAjax', //요청경로
			type: 'post',
			data: {'detailCateCodes':detailCateCodes}, //필요한 데이터
			success: function(result) {
				alert('삭제되었습니다.');
			},
			error: function() {
				alert('실패');
			}
		});
		
		setTimeout(function(){
			location.href='/admin/regCateForm';
		},20);
	}
	
}



























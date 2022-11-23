//alert('상품등록화면 연결성공~');

function searchTrAddr(){
	new daum.Postcode({
		        oncomplete: function(data) {
		            //도로명 주소
		        	 const roadAddr = data.roadAddress; 
		             document.querySelector('#tradeAddr').value = roadAddr;//내가 선택한 input태그에 밸류값으로 도로명주소를 넣어준다.
		             
		        }
		    }).open();
	
}

const mainSelect=document.querySelector('.mainCate select');
const subCate=document.querySelector('.subCate');
const detailCate=document.querySelector('.detailCate');
mainSelect.addEventListener('change',function(){
	const mainCateCode=mainSelect.value;
	$.ajax({
	   url: '/cate/selectSubCateAjax', //요청경로
	    type: 'post',
	    data:{'mainCateCode':mainCateCode}, //필요한 데이터
	    success: function(result) {
			subCate.innerText='';
			detailCate.innerText='';
			let str='';
			str+=`<select name='subCateCode' class="form-select form-select-sm mt-2">`
			for(const subCate of result)
			{
				str+=`<option value=${subCate.subCateCode}>${subCate.subCateName}</option>`;
			}
			str+=`</select>`;
			subCate.insertAdjacentHTML('afterbegin',str);
			
			subCateList();
	    },
	    error: function(){
	       alert('실패');
	    }
	});
})

function subCateList(){
	const subCateSelect=subCate.querySelector('select');
	subCateSelect.addEventListener('change',function(){
		const subCateCode=subCateSelect.value;
		
		$.ajax({
	   url: '/cate/selectDetailCateAjax', //요청경로
	    type: 'post',
	    data:{'subCateCode':subCateCode}, //필요한 데이터
	    success: function(result) {
			detailCate.innerText='';
			let str='';
			str+=`<select name='detailCateCode' class="form-select form-select-sm mt-2">`
			for(const detailCate of result)
			{
				str+=`<option value=${detailCate.detailCateCode}>${detailCate.detailCateName}</option>`;
			}
			str+=`</select>`;
			detailCate.insertAdjacentHTML('afterbegin',str);
			
			subCateList();
	    },
	    error: function(){
	       alert('실패');
	    }
	});
	})
}
alert('상품등록화면 연결성공~');

function searchTrAddr(){
	new daum.Postcode({
		        oncomplete: function(data) {
		            //도로명 주소
		        	 const roadAddr = data.roadAddress; 
		             document.querySelector('#memberAddr').value = roadAddr;//내가 선택한 input태그에 밸류값으로 도로명주소를 넣어준다.
		             
		        }
		    }).open();
	
}
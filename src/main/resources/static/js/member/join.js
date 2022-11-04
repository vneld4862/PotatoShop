
function searchAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	const roadAddr = data.roadAddress; 
        	document.querySelector('#memberAddr').value = roadAddr;
        }
    }).open();
}

		
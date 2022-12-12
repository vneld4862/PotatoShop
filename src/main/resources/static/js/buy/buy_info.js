	 
function payBtn() {
	const buyName = $('#buyerName').text();
	const mainCateCode = $('#mainCateCode').val();
	const buyTell = $('#buyerTell').text();
	const buyAddr = $('#buyerAddr').text();
	const buyItem = $('#buyerItem').val();
	const seller = $('#seller').val();
	const buyPrice = $('#buyerPrice').val();
	const buyEmail = $('#buyerEmail').val();
	
	const merchant_uid = 'ORD_' + new Date().getTime();
	
	//const IMP = window.IMP;
    IMP.init('imp25828831');
    
	// IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({ // param
		pg: "html5_inicis",
		pay_method: "card",
		merchant_uid: merchant_uid,
		name: buyItem,
//		amount: buyPrice,
		amount: 100,
		buyer_email: buyEmail,
		buyer_name: buyName,
		buyer_tel: buyTell,
		buyer_addr: buyAddr,
		buyer_postcode: ""
	}, function(rsp) { // callback
		if (rsp.success) {
		  // jQuery로 HTTP 요청
	        jQuery.ajax({
	            url: "/buy/doPay", 
	            method: "POST",
	            //headers: { "Content-Type": "application/json" },
	            data: {
	                imp_uid: rsp.imp_uid,            //결제 고유번호     
	                merchant_uid: rsp.merchant_uid,   //주문번호
	                'buyCode': merchant_uid,
	                'itemCode': $('#itemCode').val(),
	                'seller':seller,
	                'itemPrice':buyPrice,
	                'mainCateCode':mainCateCode
	                
	            }
	        }).done(function (data) {
				alert('결제가 완료되었습니다.');
				location.href = '/buy/buyResult';
	        })
	      }else {
			console.log(rsp);
			
			if(rsp.error_msg == '사용자가 결제를 취소하셨습니다'){
				alert('결제를 취소하였습니다.');
			}
			else{
				alert('일시적으로 오류가 발생했습니다.\n잠시 후 다시 시도해주세요.');
			}
			
		}
	});
}
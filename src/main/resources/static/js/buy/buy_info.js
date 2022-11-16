	 
function payBtn() {

	//const IMP = window.IMP;
    IMP.init('imp25828831');
    
	// IMP.request_pay(param, callback) 결제창 호출
	IMP.request_pay({ // param
		pg: "html5_inicis",
		pay_method: "card",
		merchant_uid: "ORD20180131-0000011",
		name: "노르웨이 회전 의자",
		amount: 64900,
		buyer_email: "gildong@gmail.com",
		buyer_name: "홍길동",
		buyer_tel: "010-4242-4242",
		buyer_addr: "서울특별시 강남구 신사동",
		buyer_postcode: "01181"
	}, function(rsp) { // callback
		if (rsp.success) {
			alert(1);
			// 결제 성공 시 로직,
		} else {
			alert("결제에 실패했습니다."+"에러코드 : "+rsp.error_code+"에러 메시지 : "+rsp.error_message);
			// 결제 실패 시 로직,
		}
	});
}
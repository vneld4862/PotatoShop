const bannerTag=document.querySelector('.banner');
const bannerStyle=window.getComputedStyle(bannerTag); //선택한 태그의 디자인 정보를 가져옴
let bannerTop=bannerStyle.getPropertyValue('top');
bannerTop=parseInt(bannerTop.substr(0,bannerTop.length-2));
//페이지에서 스크롤 변화가 일어나면 자동으로 실행되는 구문
$(window).scroll(function(){
	let currentTop = $(window).scrollTop();
	//오늘본 상품div의 상단위치= 현재스크롤바 위치+div의 상단 위치
	const newPostion=currentTop+bannerTop;
	$('.banner').stop().animate({top:newPostion+'px'},300);
});
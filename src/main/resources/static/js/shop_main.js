const bannerTag = document.querySelector('.banner');
const bannerStyle = window.getComputedStyle(bannerTag); //선택한 태그의 디자인 정보를 가져옴
let bannerTop = bannerStyle.getPropertyValue('top');
bannerTop = parseInt(bannerTop.substr(0, bannerTop.length - 2));

let tryOne=true;
$(window).scroll(function() {
	let currentTop = $(window).scrollTop();
	//오늘본 상품div의 상단위치= 현재스크롤바 위치+div의 상단 위치
	const newPostion = currentTop + bannerTop;
	$('.banner').stop().animate({ top: newPostion + 'px' }, 300);
});

function mainChart() {
	const mainCateName = [];
	const totalPrice = [];
	const totalSales = [];
	if(tryOne)
	{
		$.ajax({
			url: '/cate/mainCateChartAjax', //요청경로
			type: 'post',
			data: {}, //필요한 데이터
			success: function(result) {
				for (const mainChart of result) {
					mainCateName.push(mainChart.mainCateName);
					totalPrice.push(mainChart.totalPrice + '원');
					totalSales.push(mainChart.totalSales)
				}
				var options = {
					series: [{
						name: '카테고리 총 매출',
						type: 'column',
						data: totalPrice
					}, {
						name: '주요 카테고리 총 판매량',
						type: 'line',
						data: totalSales
					}],
					chart: {
						height: 350,
						type: 'line',
					},
					stroke: {
						width: [0, 4]
					},
					title: {
						text: '카테고리 매출상황'
						,align: 'center'
					},
					dataLabels: {
						enabled: true,
						enabledOnSeries: [1]
					},
					labels: mainCateName,
					xaxis: {
						type: 'line'
					},
					yaxis: [{
						title: {
							text: 'totalPrice',
						},
	
					}, {
						opposite: true,
						title: {
							text: 'totalSale Amount'
						}
					}]
				};
				
				var chart = new ApexCharts(document.querySelector("#chart"), options);
				chart.render();
	
			},
			error: function() {
				alert('실패');
			}
		});
	}
	tryOne=false;
}


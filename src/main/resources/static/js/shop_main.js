const bannerTag = document.querySelector('.banner');
const bannerStyle = window.getComputedStyle(bannerTag); //선택한 태그의 디자인 정보를 가져옴
let bannerTop = bannerStyle.getPropertyValue('top');
bannerTop = parseInt(bannerTop.substr(0, bannerTop.length - 2));
//페이지에서 스크롤 변화가 일어나면 자동으로 실행되는 구문
$(window).scroll(function() {
	let currentTop = $(window).scrollTop();
	//오늘본 상품div의 상단위치= 현재스크롤바 위치+div의 상단 위치
	const newPostion = currentTop + bannerTop;
	$('.banner').stop().animate({ top: newPostion + 'px' }, 300);
});

mainChart();

function mainChart() {
	const mainCateName = [];
	const totalPrice = [];
	const totalSales = [];
	$.ajax({
		url: '/cate/mainCateChart', //요청경로
		type: 'post',
		data: {}, //필요한 데이터
		success: function(result) {
			for (const mainChart of result) {
				mainCateName.push(mainChart.mainCateName);
				totalPrice.push(mainChart.totalPrice);
				totalSales.push(mainChart.totalSales)
			}
			var options = {
				series: [{
					name: "Desktops",
					data: totalPrice
				}],
				chart: {
					height: 500,
					type: 'line',
					zoom: {
						enabled: false
					}
				},
				dataLabels: {
					enabled: false
				},
				stroke: {
					curve: 'straight'
				},
				title: {
					text: 'Product Trends by Month',
					align: 'left'
				},
				grid: {
					row: {
						colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
						opacity: 0.5
					},
				},
				xaxis: {
					categories: mainCateName,
				}
			};

			var chart = new ApexCharts(document.querySelector("#chart1"), options);
			chart.render();


			var options = {
				series: totalSales,
				chart: {
					type: 'donut',
				},
				responsive: [{
					breakpoint: 480,
					options: {
						chart: {
							width: 150,
						},
						legend: {
							position: 'bottom'
						}
					}
				}],
				labels: mainCateName,
				dataLabels: {
					dropShadow: {
						blur: 3,
						opacity: 0.8
					}
				}
			};

			var chart = new ApexCharts(document.querySelector("#chart2"), options);
			chart.render();


		},
		error: function() {
			alert('실패');
		}
	});
}

function reviewDetail(){
	//ajax start
	$.ajax({
	   url: '/board/reviewDetail', //요청경로
	    type: 'post',
	    data:{'itemCode':itemCode}, //필요한 데이터
	    success: function(result) {
	      const modal = new bootstrap.Modal('#reviewDetailModal');
	      modal.show()
	      
	      
	    },
	    error: function(){
	       alert('실패');
	    }
	});
//ajax end
	
	
	
};


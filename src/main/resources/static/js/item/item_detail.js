alert('상품상세정보 페이지');

//ajax start
$.ajax({
   url: '/test/ajax1', //요청경로
    type: 'post',
    data:{}, //필요한 데이터
    success: function(result) {
      alert('aaa');
    },
    error: function(){
       alert('실패');
    }
});
//ajax end
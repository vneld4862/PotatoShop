package kh.study.team2.shop.buy.vo;

public class PageVO{//페이지 정보를 갖고 있는 객체를 만들어준다.
	private int nowPage; //현재 선택된 페이지
	private int totalDataCnt; //전체 데이터 수
	private int beginPage; //화면에 보이는 첫 페이지 -> 몇번부터 보여줄건지
	private int endPage; //화면에 보이는 마지막 페이지 -> 마지막에 몇번까지 보여줄건지
	private int displayCnt;//한 화면에 보여지는 게시글 수
	private int displayPageCnt; //한 화면에 보여지는 페이지 수 -> 1 2 3 4 5 / 5개 
	private boolean prev; //이전 버튼의 유무  -> 1앞에서는 없어야 하니까
	private boolean next; //다음 버튼의 유무  -> 마지막 뒤에는 없어야하니까 true/false로 준다.
	private int startNum;//시작 row_num
	private int endNum;//마지막 row_num
	
	//생성자
	public PageVO() {
		nowPage = 1;
		displayCnt = 10;
		displayPageCnt = 10;
		
		//페이지(PageVO)가 만들어지면 기본 세팅을 이렇게 하겠다.
	}
	
	public void setPageInfo() {//해당 메소드가 실행되면 다 실행돼서 계산이 되게끔 
		// 1  2  3  4  5 
		// 6  7  8  9  10
		//화면에 보이는 마지막 페이지 번호
		endPage = displayPageCnt * (int)Math.ceil(nowPage / (double)displayPageCnt) ;
		beginPage = endPage - displayCnt + 1;
		//전체 페이지 수
		int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayCnt);
		        //105 / 10 = 11page
		
		  //totalDataCnt 따로 구해서 세팅해줘야 한다.
		
		//next 버튼의 유무  true/false
		if(endPage < totalPageCnt) {
			next = true;
			
		}
		else {
			next = false;
			endPage = totalPageCnt;
		}
		
		//prev 버튼의 유무
		prev = beginPage == 1 ? false : true;

		
		startNum = (nowPage - 1)  * displayCnt + 1;
		endNum = nowPage * displayCnt;
		
	}     
	
	public void setNowPage(int nowPage) {
		
		this.nowPage = nowPage;
	}
	
	public int getNowPage() {
		
		return nowPage;
	}
    public void setTotalDataCnt(int totalDataCnt) {
    	
    	this.totalDataCnt = totalDataCnt;
    }
    public int getTotalDataCnt() {
    	return totalDataCnt;
    	
    }

    public void setPrev(boolean prev) {
	   this.prev = prev;
    }
    public boolean getPrev() {
	   return prev;
    }
    public void setNext(boolean next) {
	   this.next = next;
    }
    public boolean getNext() {
	   return next;
    }
   
    public int getBeginPage() {
	   return beginPage;
    }
   
    public int getEndPage() {
	   return endPage;
    }
    
    public int getStartNum() {
    	return startNum;
    }
    public int getEndNum() {
    	return endNum;
    }
    
}

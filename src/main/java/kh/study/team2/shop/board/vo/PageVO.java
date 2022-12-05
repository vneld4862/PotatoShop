package kh.study.team2.shop.board.vo;

public class PageVO {
	private int nowPage; //현재 선택된 페이지
	private int totalDataCnt; //전체 데이터 수
	private int beginPage; //화면에 보이는 첫 페이지
	private int endPage; //화면에 보이는 마지막 페이지
	private int displayCnt; //한 화면에 보여지는 게시글 수
	private int displayPageCnt; //한 화면에 보여지는 페이지 수
	private boolean prev; //이전 버튼의 유무
	private boolean next; //다음 버튼의 유무
	private int startNum; //시작 row_num
	private int endNum; //마지막 row_num
	
	//생성자 - 객체 만들면 실행됨
	public PageVO() {
		nowPage = 1;
		displayCnt = 10;
		displayPageCnt = 10;
	}
	
	public void setPageInfo() {
		//	1	2	3	4	5
		//	6	7	8	9	10
		//화면에 보이는 마지막 페이지 번호
		endPage = displayPageCnt * (int)Math.ceil(nowPage / (double)displayPageCnt);
//							5	*	Math.ceil(1 / 5.0)
//										(int)1.0
//											1
//			5
		beginPage = endPage - displayPageCnt + 1;
//현재 페이지 7
//						10		-	5		+	1;
//			6
		
		//전체 페이지 수
		int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayCnt);
		
		//next 버튼의 유무
		if(endPage < totalPageCnt) {
			next = true;
		}
		else {
			next = false;
			endPage = totalPageCnt;
		}
		
		//prev 버튼의 유무
		prev = beginPage == 1 ? false : true;
		
		startNum = (nowPage - 1) * displayCnt + 1;
		endNum = nowPage * displayCnt ;
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
	
	public boolean isPrev() {
		return prev;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	public boolean isNext() {
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

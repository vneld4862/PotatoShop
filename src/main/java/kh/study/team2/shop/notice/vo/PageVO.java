package kh.study.team2.shop.notice.vo;


public class PageVO {
	private int nowPage; //현재 선택된 페이지
	private int totalDataCnt; //전체 데이터 수
	private int beginPage; //화면에 보이는 첫 페이지
	private int endPage; //화면에 보이는 마지막 페이지
	private int displayCnt; //한 화면에 보여지는 게시글 수
	private int displayPageCnt; //한 화면에 보여지는 페이지 수
	private boolean prev; //이전 버튼의 유무 (보일때도 있고 안보일 때도 있다)
	private boolean next; //다음 버튼의 유무
	private int startNum; //시작 row_num
	private int endNum; //마지막 row_num
	
	public PageVO() {
		nowPage = 1;
		displayCnt = 10;
		displayPageCnt = 5;
	}
	
	public void setPageInfo() {

		//화면에 보이는 마지막 페이지 번호
		//Math.ceil : 숫자 올려줌
		endPage = displayPageCnt * (int)Math.ceil(nowPage / (double)displayPageCnt);
		beginPage = endPage - displayPageCnt + 1;
		//전체 페이지 수
		int totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayCnt);
	
		//next 버튼의 유무
		if(endPage < totalPageCnt) {
			next = true;
		}
		else { //next 버튼이 없을때
			next = false;
			endPage = totalPageCnt;
		}
		
		//prev 유무
		prev = beginPage == 1 ? false : true;
		
		startNum = (nowPage - 1) * displayCnt + 1;  
		
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
	public boolean getPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
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
	public int getEndnum() {
		return endNum;
	}
}







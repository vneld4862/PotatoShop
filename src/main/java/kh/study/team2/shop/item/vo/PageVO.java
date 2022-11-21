package kh.study.team2.shop.item.vo;

public class PageVO extends SearchVO
{
	private int nowPage; //현재페이지 정보
	private int totalDataCnt; //전체 데이터 수
	private int beginPage; //화면에 보이는 첫 페이지
	private int endPage; //화면에 보이는 마지막 페이지
	private int displayCnt; //한 화면에 보여지는 게시글 수
	private int displayPageCnt; //한 화면에 보여지는 페이지 수
	private boolean prev; //이전 버튼의 유무
	private boolean next; //다음 버튼의 유무
	private int startNum; //시작 row_num
	private int endNum; //마지막 row_num
	
	public PageVO()
	{
		nowPage=1;
		displayCnt=20;
		displayPageCnt=5;
	}
	//페이지 정보
	public void setPageInfo()
	{
		//화면에 보이는 마지막 페이지 번호
		//ceil : 올림함수
		endPage=displayPageCnt * (int)Math.ceil(nowPage/(double)displayPageCnt);
		System.out.println("@@@@@@@@@@@@@@end"+endPage);
		beginPage=endPage-displayPageCnt+1;
		//전체페이지수
		int totalPageCnt=(int)Math.ceil(totalDataCnt/(double)displayCnt);
		
		//next유무
		if(endPage < totalPageCnt)
		{
			next=true;
		}
		else 
		{
			next=false;
			endPage=totalPageCnt;
		}
		prev = beginPage == 1 ? false : true;
		
		startNum=(nowPage-1) * displayCnt+1;
		endNum=displayCnt * nowPage;
		System.out.println("@@@@@@@@@@@@@@s"+startNum);
		System.out.println("@@@@@@@@@@@@@@e"+endNum);
	}
	
	public void setNowPage(int nowPage)
	{
		this.nowPage=nowPage;
	}
	public int getNowPage()
	{
		return nowPage;
	}
	public void setTotalDataCnt(int totalDataCnt)
	{
		this.totalDataCnt=totalDataCnt;
	}
	public int getTotalDataCnt()
	{
		return totalDataCnt;
	}
	public void setPrev(boolean prev)
	{
		this.prev=prev;
	}
	public boolean getPrev() 
	{
		return prev;
	}
	public void setNext(boolean next)
	{
		this.next=next;
	}
	public boolean getNext()
	{
		return next;
	}
	public int getBeginPage() 
	{
		return beginPage;
	}
	public int getEndPage()
	{
		return endPage;
	}
	public int getStartNum()
	{
		return startNum;
	}
	public int getEndNum()
	{
		return endNum;
	}
}

package kh.study.team2.shop.board.vo;

import groovy.transform.ToString;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.vo.ProfileVO;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class BoardVO{
	private int boardNum;
	private String itemCode;
	private String boardTitle;
	private String boardContent;
	private String buyer;
	private String seller;
	private String regDate;
	private int starPoint;
	private ReviewImgVO reviewImgVO;
	private ItemVO itemVO;
	private ReplyVO replyVO;
	private ProfileVO profileVO;
}

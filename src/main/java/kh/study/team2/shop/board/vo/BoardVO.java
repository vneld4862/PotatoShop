package kh.study.team2.shop.board.vo;

import groovy.transform.ToString;
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
	private String memberId;
	private String regDate;
	private int starPoint;
	private ReviewImgVO reviewImgVO;
}

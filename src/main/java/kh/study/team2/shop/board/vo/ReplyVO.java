package kh.study.team2.shop.board.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class ReplyVO{
	private int replyNum;
	private String replyContent;
	private String memberId;
	private String ReplyRegDate;
	private int boardNum;
}

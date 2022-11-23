package kh.study.team2.shop.notice.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class NoticeVO extends PageVO{

	private int noticeNum;
	private String title;
	private String content;
	private String memberId;
	private String createDate;
	private int readCnt;
	
}

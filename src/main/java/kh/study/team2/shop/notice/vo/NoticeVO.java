package kh.study.team2.shop.notice.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class NoticeVO {

	private int noticeNum;
	private String title;
	private String content;
	private String memberId;
	private int createDate;
	private int readCnt;
	
}

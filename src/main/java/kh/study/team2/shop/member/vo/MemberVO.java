package kh.study.team2.shop.member.vo;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class MemberVO {
	 private String memberId;
	 private String memberPw;
	 private String	meberName;
	 private String memberNickName;
	 private String memberTell;
	 private String memberAddr;
	 private String addrDetail;
	 private String memberEmail;
	 private String memberRole;
	 private String memberStatus;
	 private String regDate; 
}

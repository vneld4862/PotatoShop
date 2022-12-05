package kh.study.team2.shop.member.vo;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberVO extends SearchVO {
	 private String memberId;
	 private String memberPw;
	 private String	memberName;
	 private String memberNickName;
	 private String memberTell;
	 private String memberAddr;
	 private String addrDetail;
	 private String memberEmail;
	 private String memberRole;
	 private String memberStatus;
	 private String regDate; 
	 private int memberRank;
	 private int salesCnt;
	 
	
}

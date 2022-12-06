package kh.study.team2.shop.member.vo;



import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberVO extends SearchVO {
	@NotBlank(message = "아이디를 입력해주세요.")
	 private String memberId;
	@NotBlank(message = "비밀번호는 필수입력입니다.")
	 private String memberPw;
	@NotBlank(message = "이름은 필수입력입니다.")
	 private String	memberName;
	@NotBlank(message = "닉네임은 필수입력입니다.")
	 private String memberNickName;
	@NotBlank(message = "휴대폰 번호는 필수입력입니다.")
	 private String memberTell;
	@NotBlank(message = "주소는 필수입력입니다.")
	 private String memberAddr;
	 private String addrDetail;
	 private String memberEmail;
	 private String memberRole;
	 private String memberStatus;
	 private String regDate; 
	 private int memberRank;
	 private int salesCnt;
	 @NotBlank(message = "인증번호는 필수입력입니다.")
	 private String certifyNum;
	 
	
}

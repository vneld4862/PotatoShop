package kh.study.team2.shop.member.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class MemberVO extends SearchVO {
	@NotBlank(message = "아이디는 필수입력입니다.")
	 private String memberId;
	@NotBlank(message = "비밀번호는 필수입력입니다.")
	 private String memberPw;
	@NotBlank(message = "이름은 필수입력입니다.")
	 private String	memberName;
	@NotBlank(message = "닉네임은 필수입력입니다.")
	 private String memberNickName;
	@NotBlank(message = "연락처는 필수입력입니다.")
	@Size(min = 10, max = 11)
	 private String memberTell;
	@NotBlank(message = "주소는 필수입력입니다.")
	 private String memberAddr;
	 private String addrDetail;
	 private String memberEmail;
	 private String memberRole;
	 private String memberStatus;
	 private String regDate; 
	 private String[] memberTells;
	 
	 public String getMemberTell() {
		 if(memberTells == null) {
			 return null;
		 }
		 else {
			 String result = "";
			 
			 for(String tell : memberTells) {
				 result += tell;
			 }
			 return result;
		 }
	 }
}

package kh.study.team2.shop.manage.vo;

import kh.study.team2.shop.member.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileVO {
   private String profileCode;
   private String originName;
   private String changedName;
   private String memberId;
   private MemberVO memberVO;
}

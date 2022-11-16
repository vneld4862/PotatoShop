package kh.study.team2.shop.member.vo;

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
}

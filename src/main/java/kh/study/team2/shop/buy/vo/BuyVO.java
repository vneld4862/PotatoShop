package kh.study.team2.shop.buy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuyVO {
	private String buyCode;
	private String memberId;
	private String buyDate;
	private String itemCode;
	private String isConfirmed;
}

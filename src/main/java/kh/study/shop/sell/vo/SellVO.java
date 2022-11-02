package kh.study.shop.sell.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SellVO {
    private String sellCode;
    private String itemCode; 
    private String memberId; 
    private String sellDate; 
    private int totalPrice;  
    private String buyReplace; 
	
}

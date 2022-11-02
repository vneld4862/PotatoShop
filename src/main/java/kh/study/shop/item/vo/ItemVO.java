package kh.study.shop.item.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemVO {
   private String itemCode;
   private String itemName;
   private int itemPrice;
   private String regDate;
   private String itemComment;
   private int itemStock;
   private String itemStatus;
   private String tradeType;
   private String tradeAddr;
   private int viewCnt;
 
}

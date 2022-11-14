package kh.study.team2.shop.item.vo;


import java.util.List;

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
   private String mainCateCode;
   private String subCateCode;
   private String detailCateCode;
   
   private List<ImgVO> imgList;
   private int cntWishList;
   
 
}

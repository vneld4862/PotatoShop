package kh.study.team2.shop.wish.vo;

import kh.study.team2.shop.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishVO {
    private String wishCode;
    private String memberId;
    private String itemCode; 
    private String wishStatus;
    
    private ItemVO itemVO;
}

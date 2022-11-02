package kh.study.shop.wish.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishVO {
    private int wishCode;
    private String memberId;
    private String itemCode; 
    private String wishStatus;
}

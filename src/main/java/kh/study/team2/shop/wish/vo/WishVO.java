package kh.study.team2.shop.wish.vo;

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

package kh.study.team2.shop.buy.vo;

import java.util.List;

import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;
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
	private List<ImgVO> imgList;
	private ItemVO itemVO;
	private BoardVO boardVO;
}

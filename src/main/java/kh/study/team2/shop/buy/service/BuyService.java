package kh.study.team2.shop.buy.service;

import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.item.vo.ItemVO;

public interface BuyService {
	void buyItem(BuyVO buyVO,ItemVO itemVO);
	String selectNextBuyCode();

	//구매 확정
	void updateBuyConfirm(String itemCode);

}

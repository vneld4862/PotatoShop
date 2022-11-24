package kh.study.team2.shop.buy.service;

import kh.study.team2.shop.buy.vo.BuyVO;

public interface BuyService {
	void buyItem(BuyVO buyVO);
	String selectNextBuyCode();
	
	//구매 확정
	void updateBuyConfirm(String itemCode);

}

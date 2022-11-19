package kh.study.team2.shop.manage.service;

import java.util.List;

import kh.study.team2.shop.buy.vo.BuyVO;

public interface ManageService {

	//구매 내역 조회
	List<BuyVO> selectBuyList(String memberId);
	

	
}

package kh.study.team2.shop.manage.service;

import java.util.List;

import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface ManageService {

	//구매 내역 조회
	List<BuyVO> selectBuyList(String memberId);
	//닉네임 수정
	void updateNickName(MemberVO memberVO);
	void updateProfileImg(ProfileVO profileVO);
	void salesStatus(ItemVO itemVO);
	
}

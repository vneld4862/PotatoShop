package kh.study.team2.shop.manage.service;

import java.util.List;

import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface ManageService {

	//구매 내역 조회
	List<BuyVO> selectBuyList(BuyVO buyVO);
	
	//총 구매 개수 조회
	int selectBuyCnt(String buyer);	

	//판매 내역 조회
	List<BuyVO> selectSalesList(BuyVO buyVO);

	//총 판매 개수 조회
	int selectSalesCnt(String seller);	
	
	//내가 쓴 리뷰 총 개수 조회
	//int selectWrittenReviewCnt(String buyer);
	
	//내 상점에 작성된 리뷰 총 개수 조회
	//int selectMyMarketReviewCnt(String seller);
	
	//닉네임 수정
	void updateNickName(MemberVO memberVO);
	//프로필 이미지 수정
	void updateProfileImg(ProfileVO profileVO);
	//판매상태 변경
	void salesStatus(ItemVO itemVO);
	
	//이미지 삭제
	void deleteImg(String imgCode);
	
	//내상점 전체 조회수
	int selectShopViewCnt(String memberId);
	
	//상품관리 - 상품목록 조회
	List<ItemVO> selectManageitemList(ItemVO itemVO);
	
	//상품관리 - 조회된 상품수량 조회(페이징 처리)
	int selectManageItemCnt(ItemVO itemVO);
}

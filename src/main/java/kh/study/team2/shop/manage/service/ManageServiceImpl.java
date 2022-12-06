package kh.study.team2.shop.manage.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

@Service("manageService")
public class ManageServiceImpl implements ManageService{
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//구매 내역 조회
	@Override
	public List<BuyVO> selectBuyList(BuyVO buyVO) {
		return sqlSession.selectList("buyMapper.selectBuyList", buyVO);
	}

	//총 구매 개수 조회
	@Override
	public int selectBuyCnt(String buyer) {
		return sqlSession.selectOne("buyMapper.selectBuyCnt", buyer);
	}	

	//판매 내역 조회
	@Override
	public List<BuyVO> selectSalesList(BuyVO buyVO) {
		return sqlSession.selectList("buyMapper.selectSalesList", buyVO);
	}
	
	//총 판매 개수 조회
	@Override
	public int selectSalesCnt(String seller) {
		return sqlSession.selectOne("buyMapper.selectSalesCnt", seller);
	}	
	
	//내가 쓴 리뷰 총 개수 조회
	//@Override
	//public int selectWrittenReviewCnt(String buyer) {
	//	return sqlSession.selectOne("buyMapper.selectWrittenReviewCnt", buyer);
	//}
	
	//내 상점에 쓰여진 리뷰 총 개수 조회
	//public int selectMyMarketReviewCnt(String seller) {
	//	return sqlSession.selectOne("buyMapper.selectMyMarketReviewCnt", seller);
	//}
	
	
	@Override
	public void updateNickName(MemberVO memberVO) {
		sqlSession.update("memberMapper.updateNickName", memberVO);
	}

	@Override
	public void updateProfileImg(ProfileVO profileVO) {
		sqlSession.update("memberMapper.updateProfileImg", profileVO);
	}

	@Override
	public void salesStatus(ItemVO itemVO) {
		sqlSession.update("itemMapper.salesStatus", itemVO);
	}


	@Override
	public void deleteImg(String imgCode) {
		sqlSession.delete("itemMapper.deleteImg", imgCode);
	}

	@Override
	public int selectShopViewCnt(String memberId) {
		return sqlSession.selectOne("itemMapper.selectShopViewCnt",memberId);
	}

	//상품관리 - 상품목록 조회
	@Override
	public List<ItemVO> selectManageitemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.selectManageitemList", itemVO);
	}
	
}

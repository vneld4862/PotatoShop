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

	@Override
	public List<BuyVO> selectBuyList(String memberId) {
		return sqlSession.selectList("buyMapper.selectBuyList", memberId);
	}

	@Override
	public List<BuyVO> selectSalesList(String memberId) {
		return sqlSession.selectList("buyMapper.selectSalesList", memberId);
	}
	
	//리뷰 총 개수 조회
	@Override
	public int selectBoardCnt() {
		return sqlSession.selectOne("boardMapper.selectBoardCnt");
	}	
	
	
	
	
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
	
}

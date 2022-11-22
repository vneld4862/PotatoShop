package kh.study.team2.shop.manage.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.buy.vo.BuyVO;
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
	public void updateNickName(MemberVO memberVO) {
		sqlSession.update("memberMapper.updateNickName", memberVO);
	}

	@Override
	public void updateProfileImg(ProfileVO profileVO) {
		sqlSession.update("memberMapper.updateProfileImg", profileVO);
	}



	
}

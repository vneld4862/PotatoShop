package kh.study.team2.shop.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void join(MemberVO memberVO) {
		sqlSession.insert("memberMapper.join", memberVO);
		sqlSession.insert("memberMapper.insertProfile", memberVO);
//		sqlSession.insert("memberMapper.insertProfile", profileVO);
	}
	@Override
	public MemberVO login(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.login", memberVO);
	}
	@Override
	public String idChk(String memberId) {
		return sqlSession.selectOne("memberMapper.idChk", memberId);
	}
	@Override
	public MemberVO selectMemberInfo(String memberId) {
		return sqlSession.selectOne("memberMapper.selectMemberInfo", memberId);
	}
	@Override
	public ProfileVO profileInfo(String memberId) {
		return sqlSession.selectOne("memberMapper.profileInfo", memberId);
	}
	@Override
	public void updateMyInfo(MemberVO memberVO) {
		sqlSession.update("memberMapper.updateMyInfo", memberVO);
	}
	@Override
	public MemberVO searchId(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.searchId", memberVO);
	}
	
	
}
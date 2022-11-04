package kh.study.team2.shop.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public void join(MemberVO memberVO) {
		sqlSession.insert("memberMapper.join", memberVO);
	}
	@Override
	public MemberVO login(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.login", memberVO);
	}
	
	
}
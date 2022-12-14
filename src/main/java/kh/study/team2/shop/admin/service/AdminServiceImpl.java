package kh.study.team2.shop.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.member.vo.MemberVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MemberVO> selectMemberList(MemberVO memberVO) {
		return sqlSession.selectList("adminMapper.selectMemberList", memberVO);
	}

	@Override
	public MemberVO selectMemberDetail(String memberId) {
		return sqlSession.selectOne("adminMapper.selectMemberDetail", memberId);
	}
	
	@Override
	public List<MemberVO> getMemberList(String memberStatus) {
		return sqlSession.selectList("adminMapper.getMemberList", memberStatus);
	}

	@Override
	public void updateMemberStatus(MemberVO memberVO) {
		sqlSession.update("adminMapper.updateMemberStatus", memberVO);
	}

	@Override
	public void updateCateStatus(MainCateVO mainCateVO) {
		sqlSession.update("adminMapper.updateCateStatus",mainCateVO);
	}
	
}

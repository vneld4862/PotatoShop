package kh.study.team2.shop.admin.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.member.vo.MemberVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MemberVO> selectMemberList(MemberVO memberVO) {
		return sqlSession.selectList("adminMapper.selectMemberList", memberVO);
	}
	
}

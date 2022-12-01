package kh.study.team2.shop.member.service;

import java.util.List;

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
	@Override
	public void deleteMember(String memberId) {
		sqlSession.update("memberMapper.deleteMember", memberId);
	}
	@Override
	public List<String> memberRank() {
		return sqlSession.selectList("memberMapper.memberRank");
	}
	@Override
	public ProfileVO detailProfile(String itemCode) {
		return sqlSession.selectOne("memberMapper.detailProfile", itemCode);
	}
	@Override
	public String searchPw(String memberEmail) {
		return sqlSession.selectOne("memberMapper.searchPw", memberEmail);
	}
	@Override
	public void sendEmail(String memberEmail, String memberName) {
//		SimpleMailMessage simpleMailMessage = new  SimpleMailMessage();
//		simpleMailMessage.setTo(memberEmail);
//		simpleMailMessage.setSubject("비밀번호 찾기");
//		
//		StringBuffer sb = new StringBuffer();
//		sb.append("가입하신 아이디는");
//		sb.append(System.lineSeparator());
//		
//		for(int i=0;i<memberName.size()-1;i++) {
//			sb.append(memberName.get(i));
//			sb.append(System.lineSeparator());
//		}
//		sb.append(memberName.get(memberName.size()-1)).append("입니다");
//		
//		simpleMailMessage.setText(sb.toString());
//		
//		
//		new Thread(new Runnable() {
//			public void run() {
//				mailSender.send(simpleMailMessage);
//			}
//		}).start();
	}
	
}
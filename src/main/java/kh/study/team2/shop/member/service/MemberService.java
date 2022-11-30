package kh.study.team2.shop.member.service;

import java.util.List;

import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface MemberService {

	void join(MemberVO memberVO);
	MemberVO login(MemberVO memberVO);
	String idChk(String memberId);
	MemberVO selectMemberInfo(String memberId);
	ProfileVO profileInfo(String memberId);
	void updateMyInfo(MemberVO memberVO);
	MemberVO searchId(MemberVO memberVO);
	
	//회원 탈퇴
	void deleteMember(String memberId);
	List<String> memberRank();
}

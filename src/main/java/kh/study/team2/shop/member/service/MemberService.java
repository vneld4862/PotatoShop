package kh.study.team2.shop.member.service;

import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface MemberService {

	void join(MemberVO memberVO);
	MemberVO login(MemberVO memberVO);
	String idChk(String memberId);
	MemberVO selectMemberInfo(String memberId);
	ProfileVO profileInfo(String memberId);
	void updateMyInfo(MemberVO memberVO);
	
}

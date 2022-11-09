package kh.study.team2.shop.member.service;

import kh.study.team2.shop.member.vo.MemberVO;

public interface MemberService {

	void join(MemberVO memberVO);
	MemberVO login(MemberVO memberVO);
	int idChk(MemberVO memberVO);
}

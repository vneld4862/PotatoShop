package kh.study.team2.shop.admin.service;

import java.util.List;

import kh.study.team2.shop.member.vo.MemberVO;

public interface AdminService {
	
	//회원 목록 조회
	List<MemberVO> selectMemberList();
}

package kh.study.team2.shop.admin.service;

import java.util.List;

import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface AdminService {
	
	//회원 목록 조회
	List<MemberVO> selectMemberList(MemberVO memberVO);
	
	//회원 정보 상세 조회
	MemberVO selectMemberDetail(String memberId);
	
	//mainCate 등록
	void inputMainCate(MainCateVO mainCateVO);
}

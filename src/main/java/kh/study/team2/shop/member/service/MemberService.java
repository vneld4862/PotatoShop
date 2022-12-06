package kh.study.team2.shop.member.service;

import java.util.List;

import com.thoughtworks.qdox.model.Member;

import kh.study.team2.shop.manage.vo.ProfileVO;
import kh.study.team2.shop.member.vo.MemberVO;

public interface MemberService {

	void join(MemberVO memberVO);
	MemberVO login(MemberVO memberVO);
	String idChk(String memberId);
	MemberVO selectMemberInfo(String memberId);
	//프로필 조회(나의 상점)
	ProfileVO profileInfo(String memberId);
	//프로필 조회(상품 상세정보 페이지)
	ProfileVO detailProfile(String itemCode);
	//내정보 조회
	MemberVO selectMyInfo(MemberVO memberVO);
	//내정보 수정
	void updateMyInfo(MemberVO memberVO);
	String searchId(MemberVO memberVO);
	String searchPw(String memberEmail);
	void initPw(MemberVO memberVO);
	
	//회원 탈퇴
	void deleteMember(String memberId);
	List<String> memberRank();
}

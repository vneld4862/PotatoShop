package kh.study.team2.shop.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.admin.service.AdminService;
import kh.study.team2.shop.member.vo.MemberVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name="adminService")
	private AdminService adminService;
	
	//회원관리 페이지 이동
	@RequestMapping("/memberManage")
	public String memberManage(Model model, MemberVO memberVO) {
		System.out.println("searchType = " + memberVO.getSearchType());
		System.out.println("searchValue = " + memberVO.getSearchValue());
		
		//회원 목록 조회
		model.addAttribute("memberList", adminService.selectMemberList(memberVO));
		
		return "content/admin/member_manage";
	}

}

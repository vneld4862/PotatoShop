package kh.study.team2.shop.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.admin.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name="adminService")
	private AdminService adminService;
	
	//회원관리 페이지 이동
	@GetMapping("/memberManage")
	public String memberManage(Model model) {
		model.addAttribute("memberList", adminService.selectMemberList());
		
		return "content/admin/member_manage";
	}

}

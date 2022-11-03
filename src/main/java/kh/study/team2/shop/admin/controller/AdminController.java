package kh.study.team2.shop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/memberManage")
	public String memberManage() {
		
		return "content/admin/member_manage";
	}

}

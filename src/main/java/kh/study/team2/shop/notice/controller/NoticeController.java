package kh.study.team2.shop.notice.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.notice.service.NoticeService;
import kh.study.team2.shop.notice.vo.NoticeVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Resource(name = "noticeService")
	NoticeService noticeService;
	
	@GetMapping("/list")
	public String list() {
		
		return "/content/notice/notice_list";
	}
	
	@GetMapping("/regNotice")
	public String regNotice() {
		return "/content/notice/notice_write";
	}
	
	@PostMapping("/regNotice")
	public String regNotice(NoticeVO noticeVO, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		noticeVO.setMemberId(user.getUsername());
		
		noticeService.insertNotice(noticeVO);
		return "redirect:/notice/list";
	}
	
	
	
}

package kh.study.team2.shop.notice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String list(NoticeVO noticeVO, Model model) {
		//공지사항 목록 조회
		model.addAttribute("noticeList", noticeService.selectNoticeList());
		return "/content/notice/notice_list";
	}
	//글 등록 페이지 이동
	@GetMapping("/regNotice")
	public String regNotice(NoticeVO noticeVO) {
		return "/content/notice/notice_write";
	}
	//글 등록
	@PostMapping("/regNotice")
	public String doRegNotice(NoticeVO noticeVO) {
//		User user = (User)authentication.getPrincipal();
//		noticeVO.setMemberId(user.getUsername());
		
		noticeService.insertNotice(noticeVO);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/noticeDetail")
	public String noticeDetail(NoticeVO noticeVO, Model model) {
		model.addAttribute("noticeDetailList", noticeService.selectNoticeDetail(noticeVO));
		return "/content/notice/notice_detail";
	}
	//글 수정 페이지 이동
	@GetMapping("/correctNotice")
	public String correctNotice(int noticeNum, Model model) {
		
		return "/content/notice/notice_correct";
	}
	//글 수정
	@PostMapping("/correctNotice")
	public String correctNotice(NoticeVO noticeVO) {
		noticeService.correctNotice(noticeVO);
		return "redirect:/notice/list";
	}
	
}

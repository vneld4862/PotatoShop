package kh.study.team2.shop.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberContorller {
	
	@GetMapping("/join")
	public String join() {
		return "content/member/join";
	}
	@PostMapping("/join")
	public String doJoin(MemberVO memberVO)
	{
		return "redirect:/item/list";
	}
}

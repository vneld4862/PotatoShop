package kh.study.team2.shop.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberContorller {
	
	@Resource(name="memberService") 
	private MemberService memberService;
	
	@GetMapping("/join")
	public String join(MemberVO memberVO) {
		return "content/member/join";
	}
	
	@PostMapping("/join")
	public String doJoin(MemberVO memberVO, Model model){
		memberService.join(memberVO);
		return "redirect:/item/list";
		
	}
	@GetMapping("/login")
	public String login(MemberVO memberVO) {
		return "content/member/login";
	}
	
	@ResponseBody
	@PostMapping("/ajaxLogin")
	public boolean ajaxLogin(MemberVO memberVO, HttpSession session) {
		MemberVO loginInfo = memberService.login(memberVO);
		
		if(loginInfo != null) { 
			session.setAttribute("loginInfo", loginInfo);
		}
		return loginInfo == null ? false : true;
	}
	
	@ResponseBody
	@PostMapping("/checkDuplId")
	public boolean ID_Check(String memberId) {
		System.out.println(memberId);
		String result = memberService.idChk(memberId);
		
		//id 사용 가능
		if(result == null) {
			return true;
		}
		else {
			return false;
		}
	}
}

package kh.study.team2.shop.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO memberVO) throws Exception {
		int result = memberService.idChk(memberVO);
		return result;
	}

//	// 회원가입 post
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String postRegister(MemberVO memberVO) throws Exception {
//		logger.info("post register");
//		int result = memberService.idChk(memberVO);
//		try {
//			if(result == 1) {
//				return "/member/join";
//			}else if(result == 0) {
//				memberService.join(memberVO);
//			}
//			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
//			// 존재하지 않는다면 -> register
//		} catch (Exception e) {
//			throw new RuntimeException();
//		}
//		return "redirect:/member/login";
//	}
}

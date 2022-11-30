package kh.study.team2.shop.member.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberContorller {
	
	@Resource(name="memberService") 
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("/join")
	public String join(MemberVO memberVO) {
		return "content/member/join";
	}
	
	@PostMapping("/join")
	public String doJoin(MemberVO memberVO, Model model){

		//비밀번호 암호화
		String pw = encoder.encode(memberVO.getMemberPw()); //memberVO 안에서 input으로 입력받은 비밀번호를 가지고 와서 암호화 후 이름을 pw로 지정
		memberVO.setMemberPw(pw); //암호화한 값 pw를 memberVO의 비밀번호로 세팅
		
		memberService.join(memberVO);
		
		return "redirect:/item/list";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "content/member/accessDenied";
	}
	
	@GetMapping("/login")
	public String login(MemberVO memberVO) {
		return "content/member/login";
	}

	
	//로그인 시도하면 login_result.html로 보내 준다
	@GetMapping("/loginResult")
	public String loginResult() {	
		
		return "content/member/login_result";
	}

	//아이디 중복확인
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
	
	//아이디 찾기 페이지 이동
	@GetMapping("/searchId")
	public String searchId(){
		return "content/member/search_id";
	}
	
	//아이디 찾기 실행
	@PostMapping("/doSearchId")
	public String doSearchId(MemberVO memberVO, Model model){
		MemberVO member = memberService.searchId(memberVO);
		
		if(member == null) { 
			model.addAttribute("check", 1);
		} else { 
			model.addAttribute("check", 0);
			model.addAttribute("id", member.getMemberId());
		}
		return "redirect:/member/login";
	}
	/*
	 * //아이디 찾기 결과 페이지
	 * 
	 * @PostMapping("/searchIdResult") public String searchIdResult() { return
	 * "content/member/search_id_result"; }
	 */
	
	//비밀번호 찾기 페이지 이동
	@GetMapping("/searchPw")
	public String searchPw(){
		return "content/member/search_pw";
	}
	
	//비밀번호 찾기 실행
	@PostMapping("/doSearchPw")
	public String doSearchPw(MemberVO memberVO, Model model){
		
		return "redirect:/member/login";
	}
	//비밀번호 찾기 결과
	@PostMapping("/searchPwResult")
	public String searchPwResult(MemberVO memberVO, Model model){
		
		return "redirect:/member/login";
	}
	
	//회원 탈퇴
	@ResponseBody
	@PostMapping("/deleteMember")
	public void deleteMember(String memberId) {
		
		memberService.deleteMember(memberId);
	}
	
	

	
	
}

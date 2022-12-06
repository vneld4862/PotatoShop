package kh.study.team2.shop.member.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.member.service.MailService;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberContorller {

	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "mailService")
	private MailService mailService;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/join")
	public String join(MemberVO memberVO) {
		return "content/member/join";
	}

	@PostMapping("/join")
	public String doJoin(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) {

		//validation 체크(데이터 유효성 검증)
		if(bindingResult.hasErrors()) { 
			return "content/member/join";
		}
		
		// 비밀번호 암호화
		String pw = encoder.encode(memberVO.getMemberPw()); // memberVO 안에서 input으로 입력받은 비밀번호를 가지고 와서 암호화 후 이름을 pw로 지정
		memberVO.setMemberPw(pw); // 암호화한 값 pw를 memberVO의 비밀번호로 세팅

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

	// 로그인 시도하면 login_result.html로 보내 준다
	@GetMapping("/loginResult")
	public String loginResult() {
		return "content/member/login_result";
	}

	// 아이디 중복확인
	@ResponseBody
	@PostMapping("/checkDuplId")
	public boolean ID_Check(String memberId) {
		System.out.println(memberId);
		String result = memberService.idChk(memberId);

		// id 사용 가능
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}

	// 아이디 찾기 페이지 이동
	@GetMapping("/searchId")
	public String searchId() {
		return "content/member/search_id";
	}

	// 아이디 찾기 실행
	@ResponseBody
	@PostMapping("/doSearchId")
	public String find_id(MemberVO memberVO) {
		String memberId = memberService.searchId(memberVO);
			
		return memberId;
	}

	// 비밀번호 찾기 페이지 이동
	@GetMapping("/searchPw")
	public String searchPw() {
		return "content/member/search_pw";
	}
	
	// 메일로 임시 비밀번호 전송
	@ResponseBody
	@PostMapping("/sendEmail") 
	public void sendEmail(MemberVO memberVO){ 
		//String memberPw = memberService.searchPw(memberEmail);
		
		//메일 보내기
		String imsiPw = getImsiPw();
		mailService.sendPw(memberVO.getMemberEmail(), imsiPw);
		
		//초기화 비밀번호를 암호화 시킴
		memberVO.setMemberPw(encoder.encode(imsiPw));    
		
		//메일로 보낸 비밀번호로 디비 정보를 변경
		memberService.initPw(memberVO);
		
	}
	
	//임시 비밀번호 발급
	public String getImsiPw() {
		String[] charSet = new String[] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
                };
		
		String imsiPw = "";
		for(int i = 0 ; i < 4 ; i++) {
			Random r = new Random();
			int index = r.nextInt(charSet.length); 
			String pw = charSet[index];
			imsiPw = imsiPw + pw;
		}
		
		return imsiPw;
	}
	
	// 회원 탈퇴
		@ResponseBody
		@PostMapping("/deleteMember")
		public void deleteMember(String memberId) {

			memberService.deleteMember(memberId);
		}
	
	
}

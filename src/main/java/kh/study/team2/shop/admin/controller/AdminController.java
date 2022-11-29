package kh.study.team2.shop.admin.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.admin.service.AdminService;
import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.board.vo.BoardVO;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name="adminService")
	private AdminService adminService;
	
	@Resource(name = "cateService")
	private CateService cateService;

	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	
	//모든 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	
	//회원관리 페이지 이동
	@RequestMapping("/memberManage")
	public String memberManage(Model model
							, MemberVO memberVO
							, Authentication authentication) {
		
		//회원 목록 조회
		model.addAttribute("memberList", adminService.selectMemberList(memberVO));
		
		User user = (User)authentication.getPrincipal();
		
		//내가 쓴 글 조회
		model.addAttribute("writtenReviewList", boardService.selectWrittenReviewList(user.getUsername()));
		
		return "content/admin/member_manage";
	}

	//회원 정보 상세 조회
	@ResponseBody
	@PostMapping("/selectMemberDetail")
	public MemberVO selectMemberDetail(String memberId) {
		
		return adminService.selectMemberDetail(memberId);
	}
	
	//다른 상점에 남긴 후기 조회
	@ResponseBody
	@PostMapping("/selectWrittenReview")
	public List<BoardVO> selectWrittenReview(String buyer) {

		return boardService.selectWrittenReviewList(buyer);
	}
	
	//회원 상태별 조회
	@ResponseBody //페이지 이동 없이 Ajax 실행 
	@PostMapping("/memberListAjax")
	public List<MemberVO> getMemberListAjax(String memberStatus) {
		List<MemberVO> list = adminService.getMemberList(memberStatus);

		return list; //Ajax의 result로 전달
	}
	
	//회원 상태 변경 라디오 버튼 클릭 시 실행
	@ResponseBody
	@PostMapping("/changeStatusAjax")
	public void changeStatus(MemberVO memberVO) {
		adminService.updateMemberStatus(memberVO);
	}
	
	
	
	
	
	
	
	
	
	
	
	//카테고리 생성페이지 이동
	@GetMapping("/regCateForm")
	public String cateForm(Model model,SubCateVO subCateCode,DetailCateVO detailCateVO) {
		model.addAttribute("mainCateList",cateService.mainCateList());
		model.addAttribute("subCateList",cateService.subCateList(subCateCode));
		model.addAttribute("detailCateList",cateService.detailCateList(detailCateVO));
		return "content/admin/reg_cate_form";
	}
	
	@ResponseBody
	@PostMapping("/mainCateAjax")
	public String inputMainCateName(MainCateVO mainCateVO)
	{
		cateService.inputMainCate(mainCateVO);
		return cateService.selectNextMainCateCode();
	}
	
	@ResponseBody
	@PostMapping("/subCateAjax")
	public String inputSubCateName(SubCateVO subCateVO)
	{
		cateService.inputSubCate(subCateVO);
		return cateService.selectNextSubCateCode();
	}
	
	@ResponseBody
	@PostMapping("/detailCateAjax")
	public String inputDetailCateName(DetailCateVO detailCateVO)
	{
		cateService.inputDetailCate(detailCateVO);
		return cateService.selectNextDetailCateCode();
	}
	
	@ResponseBody
	@PostMapping("/selectSubCateAjax")
	public List<SubCateVO> selectSubCateName(String mainCateCode)
	{
		return cateService.subCateInMainCate(mainCateCode);
	}
	
	@ResponseBody
	@PostMapping("/deleteMainCateAjax")
	public void deleteMainCate(String mainCateCodes,MainCateVO mainCateVO)
	{
		String[] mainCateArr=mainCateCodes.split(",");
		List<String> mainCateCodeList=Arrays.asList(mainCateArr);
		mainCateVO.setMainCateCodeList(mainCateCodeList);
		cateService.deleteMainCateList(mainCateVO);
	}
	
	@ResponseBody
	@PostMapping("/deleteSubCateAjax")
	public void deleteSubCate(String subCateCodes,SubCateVO subCateVO)
	{
		String[] subCateArr=subCateCodes.split(",");
		List<String> subCateCodeList=Arrays.asList(subCateArr);
		subCateVO.setSubCateCodeList(subCateCodeList);
		cateService.deleteSubCateList(subCateVO);
	}
	
	@ResponseBody
	@PostMapping("/deleteDetailCateAjax")
	public void deleteDetailCate(String detailCateCodes,DetailCateVO detailCateVO)
	{
		String[] detailCateArr=detailCateCodes.split(",");
		List<String> detailCateCodeList=Arrays.asList(detailCateArr);
		detailCateVO.setDetailCateCodeList(detailCateCodeList);
		cateService.deleteDetailCateList(detailCateVO);
	}
}

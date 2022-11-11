package kh.study.team2.shop.admin.controller;

import java.util.List;

import javax.annotation.Resource;

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
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.member.vo.MemberVO;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource(name="adminService")
	private AdminService adminService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="itemService")
	private ItemService itemService;
	
	@Resource(name = "cateService")
	private CateService cateService;
	
	
	//모든 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	
	//회원관리 페이지 이동
	@RequestMapping("/memberManage")
	public String memberManage(Model model, MemberVO memberVO) {
		//System.out.println("searchType = " + memberVO.getSearchType());
		//System.out.println("searchValue = " + memberVO.getSearchValue());
		
		//회원 목록 조회
		model.addAttribute("memberList", adminService.selectMemberList(memberVO));
		
		return "content/admin/member_manage";
	}
	
	//내 상점 페이지 이동 //MemberController 건드리는 사람 없을 때 옮기기
	@GetMapping("/myMarket")
	public String myMarket(Model model, String memberId) {
		memberId = "test"; //임시 아이디 값
		
		//내 상점 후기 목록 조회
		model.addAttribute("boardList", boardService.selectBoardList(memberId));
		List<ItemVO> itemList = itemService.selectItemList();
		model.addAttribute("itemList", itemList);
		return "content/member/my_market"; 
	}

	//상품관리 페이지 이동
	@GetMapping("/itemManage")
	public String itemManage() {
		
		return "content/admin/item_manage";
	}
	
	//회원 정보 상세 조회
	@ResponseBody
	@PostMapping("/selectMemberDetail")
	public MemberVO selectMemberDetail(String memberId) {
		
		return adminService.selectMemberDetail(memberId);
	}
	
	//카테고리 생성페이지 이동
	@GetMapping("/regCateForm")
	public String cateForm(Model model,SubCateVO subCateCode,DetailCateVO detailCateVO) {
		model.addAttribute("mainCateList",cateService.mainCateList());
		return "content/admin/reg_cate_form";
	}
	
	@ResponseBody
	@PostMapping("/mainCateAjax")
	public void inputMainCateName(MainCateVO mainCateVO)
	{
		cateService.inputMainCate(mainCateVO);
	}
	
	@ResponseBody
	@PostMapping("/subCateAjax")
	public void inputSubCateName(SubCateVO subCateVO)
	{
		cateService.inputSubCate(subCateVO);
	}
	
	@ResponseBody
	@PostMapping("/detailCateAjax")
	public void inputDetailCateName(DetailCateVO detailCateVO)
	{
		cateService.inputDetailCate(detailCateVO);
	}
	
	@ResponseBody
	@PostMapping("/selectSubCateAjax")
	public List<SubCateVO> selectSubCateName(String mainCateCode)
	{
		return cateService.subCateInMainCate(mainCateCode);
	}
	
}

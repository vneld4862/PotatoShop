package kh.study.team2.shop.manage.controller;

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

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.buy.service.BuyService;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.service.ManageService;
import kh.study.team2.shop.member.service.MemberService;
import kh.study.team2.shop.member.vo.MemberVO;


@Controller
@RequestMapping("/manage")
public class ManageController {
	@Resource(name="manageService")
	private ManageService manageService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="itemService")
	private ItemService itemService;

	@Resource(name="cateService")
	private CateService cateService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="buyService")
	private BuyService buyService;
	
	
	//모든 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	//내 상점 페이지 이동
	@GetMapping("/myMarket")
	public String myMarket(Model model, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		
		//회원 정보 조회
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
				
		//내 상점 후기 목록 조회
		model.addAttribute("boardList", boardService.selectBoardList(user.getUsername()));
		
		List<ItemVO> itemList = itemService.selectItemList();
		model.addAttribute("itemList", itemList);
		
		//내가 다른 상점에 남긴 후기 목록 조회
		model.addAttribute("writtenReviewList", boardService.selectWrittenReviewList(user.getUsername()));
		
		
		return "content/manage/my_market"; 
	}	
	
	//내 정보
	@GetMapping("/myInfo")
	public String myInfo(Model model, String memberId) {
		MemberVO memberInfo = memberService.selectMemberInfo(memberId);
		model.addAttribute("myInfo", memberInfo);
		return"content/manage/my_info";
	}
	
	//내 정보 수정
	@PostMapping("/updateMyInfo")
	public String updateMyInfo(MemberVO memberVO) {
		memberService.updateMyInfo(memberVO);
		return "redirect:/item/memberItemList";//my_market으로 변경예정.
	}
	
	//프로필 이미지 수정
	@PostMapping("/profileImg")
	public String profileImg(String profileCode) {
		System.out.println(profileCode);
		
		return "redirect:/item/memberItemList";
	}
	

	
	//상품등록 페이지로 이동
	@GetMapping("/regItemForm")
	public String regItemForm(Model model) {
		List<MainCateVO> mainCateList = cateService.mainCateList();
		System.out.println(mainCateList);
		model.addAttribute("mainCateList", mainCateList);
		
		return "content/manage/reg_item";
	}
	
	
	//상품관리 페이지 이동
	@GetMapping("/itemManage")
	public String itemManage(Authentication authentication, Model model, @RequestParam(defaultValue = "2") String menu) {
		model.addAttribute("menu", menu);
		
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		
		List<ItemVO> itemList = itemService.memberItemList(memberId);
		model.addAttribute("itemList", itemList);
		
		return "content/manage/item_manage";
	}
	
	//상품 수정 화면으로 이동
	@GetMapping("/updateForm")
	public String updateForm(String itemCode, Model model) {
		System.out.println(itemCode);
		model.addAttribute("itemInfo", itemService.selectItemDetail(itemCode));
		
		return "content/manage/update_item";
	}
	
	//상품 수정
	@PostMapping("/updateItem")
	public String updateItem(ItemVO itemVO) {
		itemService.updateItem(itemVO);
		
		return"redirect:/manage/itemManage";
	}
	
	
	//상품 삭제
	
	@GetMapping("/deleteItem")
	public String deleteItem(String itemCode) {
		itemService.deleteItem(itemCode);
		
		return"redirect:/manage/itemManage";
	}
	
	
	
	//구매 내역 페이지 이동
	@GetMapping("/buyList")
	public String buyList(String itemCode
						, Model model
						, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		
		//구매 내역 조회
		model.addAttribute("buyList", manageService.selectBuyList(user.getUsername()));
		
		
		
		return "content/manage/buy_list";
	}
	
	//판매 내역 페이지 이동
	@GetMapping("/sellList")
	public String sellList() {
		
		return "content/manage/sell_list";
	}
}

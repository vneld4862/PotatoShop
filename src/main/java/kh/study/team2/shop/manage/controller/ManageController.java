package kh.study.team2.shop.manage.controller;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.study.team2.shop.board.service.BoardService;
import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.manage.service.ManageService;

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
	
	
	//모든 메소드가 실행되기 전에 무조건 실행되는 메소드
	@ModelAttribute
	public void test(@RequestParam(defaultValue = "1") String menu, Model model) {
		model.addAttribute("menu", menu);
	}
	
	//내 상점 페이지 이동
	@GetMapping("/myMarket")
	public String myMarket(Model model, String memberId) {
		memberId = "test"; //임시 아이디 값
		
		//내 상점 후기 목록 조회
		model.addAttribute("boardList", boardService.selectBoardList(memberId));
		List<ItemVO> itemList = itemService.selectItemList();
		model.addAttribute("itemList", itemList);
		
		return "content/manage/my_market"; 
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
	public String itemManage() {
		
		return "content/manage/item_manage";
	}
	
	//구매내역 페이지 이동
	@GetMapping("/buyList")
	public String buyList() {
		
		return "content/manage/buy_list";
	}
	
	//판매내역 페이지 이동
	@GetMapping("/sellList")
	public String sellList() {
		
		return "content/manage/sell_list";
	}
}

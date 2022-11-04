package kh.study.team2.shop.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ItemVO;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Resource(name="itemService")
	private ItemService itemService;
	
	@GetMapping("/list")
	public String list() {
		
		return "content/shop_main";
		
	}
	
	//판매하기 버튼 클릭 -> 상품등록 페이지로 이동
	@GetMapping("/regItemForm")
	public String regItemForm() {
		
		return "content/item/regItem";
	}
	
	
	//상품등록 버튼 클릭 -> 메인화면으로 이동(상품관리페이지 이동으로 변경예정)
	@PostMapping("/regItem")
	public String regItem(ItemVO itemVO) {
		System.out.println(itemVO);
		//insert쿼리 실행
		itemService.insertItem(itemVO);
		return "content/item/itemList";
	}
	
}

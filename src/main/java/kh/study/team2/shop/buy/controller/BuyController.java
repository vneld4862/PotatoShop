package kh.study.team2.shop.buy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.item.vo.ItemVO;
import kh.study.team2.shop.member.service.MemberService;

@Controller
@RequestMapping("/buy")
public class BuyController {
	
	@Resource(name = "itemService")
	private ItemService itemService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	
	@GetMapping("/buyInfo")
	public String buyInfo(String itemCode, Model model) {
		ItemVO itemVO = itemService.selectItemDetail(itemCode);
		model.addAttribute("item", itemVO);		
		//model.addAttribute("memberInfo", memberService.selectMemberInfo());
		
		return "content/buy/buy_info";
	}
	
	
}


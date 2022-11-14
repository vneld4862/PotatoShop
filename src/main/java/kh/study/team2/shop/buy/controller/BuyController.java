package kh.study.team2.shop.buy.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.item.service.ItemService;
import kh.study.team2.shop.member.service.MemberService;

@Controller
@RequestMapping("/buy")
public class BuyController {
	
	@Resource(name = "itemService")
	private ItemService itemService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	//바로구매
	@GetMapping("/buyInfo")
	public String buyInfo(String itemCode, Model model, Authentication authentication) {
		model.addAttribute("item", itemService.selectItemDetail(itemCode));		
		
		User user = (User)authentication.getPrincipal();
		
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
		
		return "content/buy/buy_info";
	}
	
	
	
}


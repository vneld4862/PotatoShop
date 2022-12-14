package kh.study.team2.shop.buy.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.buy.service.BuyService;
import kh.study.team2.shop.buy.vo.BuyVO;
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
	
	@Resource(name = "buyService")
	private BuyService buyService;
	
	//바로구매 -> 구매정보
	@GetMapping("/buyInfo")
	public String buyInfo(String itemCode, Model model, Authentication authentication) {
		model.addAttribute("item", itemService.selectItemDetail(itemCode));		
		
		User user = (User)authentication.getPrincipal();
		
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
		//다음에 저장되어야하는 구매코드를 조회
		model.addAttribute("nextBuyCode", buyService.selectNextBuyCode());
		
		return "content/buy/buy_info";
	}
	
	
	//결제
	@ResponseBody
	@PostMapping("/doPay")
	public String doPay(BuyVO buyVO,ItemVO itemVO, String imp_uid, String merchant_uid , Authentication authentication) {
		
		System.out.println("---------------------------------------");
		System.out.println("imp_uid : " + imp_uid);
		System.out.println("merchant_uid : " + merchant_uid);
		System.out.println("---------------------------------------");
		
		System.out.println("@@@@@@@@@@"+itemVO);
		User user = (User)authentication.getPrincipal();
		buyVO.setBuyer(user.getUsername());
		itemVO.setMemberId(buyVO.getSeller());
		buyService.buyItem(buyVO,itemVO);
		
		return "content/manage/buy_list";
	}
	
	@GetMapping("/buyResult")
	public String buyResult(Model model, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		model.addAttribute("memberInfo", memberService.selectMemberInfo(user.getUsername()));
		
		return "content/buy/buy_result";
	}
		
	//구매 확정
	@ResponseBody
	@PostMapping("/buyConfirm")
	public void buyConfirm(String itemCode) {
		
		buyService.updateBuyConfirm(itemCode);
	}
	
	
	
	
	
}


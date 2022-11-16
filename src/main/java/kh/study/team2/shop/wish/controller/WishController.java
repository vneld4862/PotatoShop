package kh.study.team2.shop.wish.controller;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.wish.service.WishService;
import kh.study.team2.shop.wish.vo.WishVO;

@Controller
@RequestMapping("/wish")
public class WishController {
	@Resource(name = "wishService")
	private WishService wishService;
	
	
	//찜버튼 클릭 시 실행 
	@ResponseBody
	@PostMapping("/insertWish")
	public void insertWish(WishVO wishVO
						   , Authentication authentication) {
		
		System.out.println("찜을 해보자^^");
		User user = (User)authentication.getPrincipal();
		String memberId = user.getUsername();
		wishVO.setMemberId(memberId);
		
		wishService.insertWish(wishVO);
	}
	
	

}

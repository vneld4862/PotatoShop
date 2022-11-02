package kh.study.team2.shop.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

	
	@GetMapping("/list")
	public String list() {
		
		return "content/shop_main";
		
	}
	@GetMapping("/regItemForm")
	public String regItemForm() {
		
		return "content/item/regItem";
	}
	
	@PostMapping("/regItem")
	public String regItem() {
		
		return "";
	}
	
}

package kh.study.team2.shop.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
@Controller
public class ChattingController {
	
	@RequestMapping("/pop")
	public String popup()
	{
		return "content/chatting/potato_chat";
	}
	
}

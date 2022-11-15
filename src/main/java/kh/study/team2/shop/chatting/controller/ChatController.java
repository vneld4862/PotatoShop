package kh.study.team2.shop.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/potatoChat")
@Controller
public class ChatController {
	
	@RequestMapping("/pop")
	public String popup(){
		return "content/chatting/potato_chat";
	}
	
	@GetMapping("/chat")
	public String chatGET(){
		log.info("@ChatController, pop GET()");
		return "chat";
	}
}

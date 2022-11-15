package kh.study.team2.shop.test;

//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@Log4j2
//@RequestMapping("/chat")
//public class ChatController {
//	
//	@Resource(name="chatService")
//	ChatService chatService;
//	
//	//채팅페이지로 이동하고 채팅방 목록 보여줌
//	@GetMapping("/chatForm")
//	public String chat(Model model) {
//		
//		log.info("# All Chat Rooms");
////		model.addAttribute(null, model);
//		
//		return "/pages/chat/chatting";
//	}
//	
//	//채팅방 목록페이지로 이동
//	@GetMapping("/chat_room_list")
//	public String chat_loom_list(Model model) {
//		
//		model.addAttribute("list", chatService.selectChatRoomList());
//		
//		return "/pages/chat/chat_room_list";
//	}
//	//채팅방 조회
//	@GetMapping("/chat_room")
//	public String getRoom(String roomId,String roomName, Model model){
//	
//	    log.info("# get Chat Room, roomID : " + roomId);
//	
////	    model.addAttribute("room", repository.findRoomById(roomId));
//	    model.addAttribute("roomId", roomId);
//	    model.addAttribute("roomName", roomName);
//	    
//	    //채팅메세지 보내주기
//	    model.addAttribute("messageList", chatService.selectListChatMessage(roomId));
//	    
//
//	    
//	    return "/pages/chat/chat_room";
//	}
//	
//
//}

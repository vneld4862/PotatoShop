package kh.study.team2.shop.chatting.vo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomVO {
	
	private String roomId;
    private String roomName;
    
    private Set<WebSocketSession> sessions = new HashSet<>();

}

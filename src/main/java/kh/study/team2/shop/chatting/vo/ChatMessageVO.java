package kh.study.team2.shop.chatting.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageVO {
	
	
	private int messageNum;
	/**
     * 송신자 id
     */
    private String userId;

    /**
     * 수신자 id
     */
//    private String receiverUserId;

    /**
     * 메시지 내용
     */
    private String message;

    /**
    * 채팅방 id
    */
    private String roomId;
}

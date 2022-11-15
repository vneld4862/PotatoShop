package kh.study.team2.shop.test;

import java.util.List;


public interface ChatService {
	//채팅방 목록 조회
	List<ChatRoomVO> selectChatRoomList();
	
	//메세지 db저장
	void insertMessage(ChatMessageVO message);
	
	//채팅방 메세지 조회
	List<ChatMessageVO> selectListChatMessage(String roomId);
}

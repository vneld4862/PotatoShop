package kh.study.team2.shop.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

@Service
@ServerEndpoint(value="/chat")
public class WebSocketChat {
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());

	//클라이언트로부터 메시지가 전달되면 WebSocketChatt 클래스의 onMessage메서드에 의해  clients에 있는 모든 session에 메시지를 전달합니다.
	@OnMessage
	public void onMessage(String msg, Session session) throws Exception{
		System.out.println("receive message : " + msg);
		
		for(Session s : clients) {
			System.out.println("send data : " + msg);
			s.getBasicRemote().sendText(msg);
		}
	}
	
	//클라이언트가 /chatt 으로 접속하면 실행 clients 에 session 이 존재하지 않으면 clients에 접속
	@OnOpen
	public void onOpen(Session s) {
		System.out.println("open session : " + s.toString());
		
		if(!clients.contains(s)) {
			clients.add(s);
			System.out.println("session open :" + s);
		}else {
			System.out.println("이미 연결된 session");
		}

	}
	
	//클라이언트가 url을 바꾸거나 브라우저를 종료하면 자동으로 onClose() 메서드가 실행되며 해당 클라이언트 정보를 clients에서 제거합니다.
	@OnClose
	public void onClose(Session s) {
		System.out.println("session close : " + s);
		clients.remove(s);

	}
}

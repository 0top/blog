package top.zerotop.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月30日下午7:50:39
 */
public class SocketHandler extends TextWebSocketHandler {

	public final static List<WebSocketSession> socketSession = Collections.synchronizedList(new ArrayList<>());

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("get message "+message.getPayload());

		for (WebSocketSession websession : socketSession) {
			websession.sendMessage(new TextMessage("收到新消息"+message.getPayload()));
		}


		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		if (session.isOpen()) {
			socketSession.add(session);
		}
		session.sendMessage(new TextMessage("id 为 "+ session.getId() + " 用户建立连接"));

		System.out.println("session id is : "+session.getId());
		super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(" websocket close ");
		socketSession.remove(session);
		super.afterConnectionClosed(session, status);
	}
}

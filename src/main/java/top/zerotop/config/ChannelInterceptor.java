package top.zerotop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import top.zerotop.websocket.RedisService;
import top.zerotop.websocket.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChannelInterceptor extends ChannelInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(ChannelInterceptor.class);

    @Autowired
    private RedisService redisMsgService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                Object name = ((Map) raw).get("name");
                if (name instanceof LinkedList) {
                    for (Object str : ((LinkedList) name)) {
                        System.out.println(str.toString());
                    }
                    accessor.setUser(new User(((LinkedList) name).get(0).toString()));
                }
            }
        }

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {

                Map<Object, Object> map = ((Map) raw);
                try {
                    String dist = ((List<String>) map.get("destination")).get(0);
                    String id = ((List<String>) map.get("id")).get(0);

                    redisMsgService.getRedisMsgByIdAndTopic(id ,dist);
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        return message;
    }
}

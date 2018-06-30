package top.zerotop.blog.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *@author 作者: zerotop
 *@createDate 创建时间: 2018年6月30日下午7:42:12
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig {
	
    @Bean
    public WebSocketHandler myHandler() {
        return new MySocketHandler();
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	System.out.println("register-- websocket ");
    	
        registry.addHandler(myHandler(), "/websocket").addInterceptors(new MyWebSocketInterceptor()).setAllowedOrigins("*");;
//        registry.addHandler(myHandler(), "/websocket/sockjs").addInterceptors(new MyWebSocketInterceptor()).withSockJS();
    }
}

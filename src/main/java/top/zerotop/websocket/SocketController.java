package top.zerotop.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SocketController {

    @Autowired
    private SimpMessagingTemplate template;
    private Message<String> res;

    @MessageMapping("/getmsg")
    @SendTo("/topic/greeting")
    public @ResponseBody
    ResMsg getMsg(@Payload() String payload) {

        ResMsg msg = new ResMsg();
        msg.setMsg(payload.toString());

        template.convertAndSend("/topic/msg", msg);

//        template.convertAndSendToUser("1","/topic/msg", msg );
        System.out.println();

        return msg;
    }

    @MessageMapping ("/greeting")
    public String handler(@Payload() String payload) {
        System.out.println("enter greeting");

        ResMsg msg = new ResMsg();
        msg.setMsg(payload.toString());

        template.convertAndSend("/topic/greet", msg);

        return "[" + System.currentTimeMillis() + ": " ;
    }

    @ExceptionHandler(value = Exception.class)
    public void getException(Exception e) {
        System.out.println("something wrong "+e.getClass()+" : "+e.getMessage());
    }

}

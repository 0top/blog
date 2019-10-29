package top.zerotop.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void getRedisMsgByTopic(String topic) {

        System.out.println("new send msg to subscribe:"+topic+" msg");

        List<String> msgList = redisTemplate.opsForList().range(topic, 0, -1);

        for (String str: msgList) {
            template.convertAndSend(topic, (ResMsg)JSON.parseObject(str, ResMsg.class));
        }

    }

    @Override
    public void getRedisMsgByIdAndTopic(String id,String topic) {

        System.out.println("new send msg to subscribe:"+topic+" msg");

        List<String> msgList = redisTemplate.opsForList().range(topic, 0, -1);

        for (String str: msgList) {
            template.convertAndSendToUser(id, topic, (ResMsg)JSON.parseObject(str, ResMsg.class));
        }

    }


    @Override
    public long addRedisMsg(String topic, ResMsg msg) {
        return redisTemplate.opsForList().leftPush(topic, JSON.toJSONString(msg));
//        return 1L;
    }
}

package top.zerotop.websocket;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void getRedisMsgByTopic(String topic) {
        logger.info("===> query history message from topic: {}", topic);

        List<String> msgList = redisTemplate.opsForList().range(topic, 0, -1);

        for (String str : msgList) {
            template.convertAndSend(topic, (ResMsg) JSON.parseObject(str, ResMsg.class));
        }
    }

    @Override
    public void getRedisMsgByIdAndTopic(String id, String topic) {
        logger.info("===> receive subscribe message: id: {}, topic: {}", id, topic);

        List<String> msgList = redisTemplate.opsForList().range(topic, 0, -1);
        System.out.println(msgList.size());

        for (String str : msgList) {
            System.out.println(str);
            template.convertAndSendToUser(id, topic, (ResMsg) JSON.parseObject(str, ResMsg.class));
        }

    }


    @Override
    public long addRedisMsg(String topic, ResMsg msg) {
        return redisTemplate.opsForList().leftPush(topic, JSON.toJSONString(msg));
//        return 1L;
    }
}

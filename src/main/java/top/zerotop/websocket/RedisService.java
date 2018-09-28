package top.zerotop.websocket;

public interface RedisService {

    void getRedisMsgByTopic(String topic) ;

    long addRedisMsg(String topic, ResMsg msg);

}

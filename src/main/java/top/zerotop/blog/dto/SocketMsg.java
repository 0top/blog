package top.zerotop.blog.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "socket_msg")
public class SocketMsg {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    public String username;

    @Column(name = "msg")
    private String msg;

    @Column(name = "gmt_create")
    private String gmtCreate;

    @Column(name = "topic")
    private String topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

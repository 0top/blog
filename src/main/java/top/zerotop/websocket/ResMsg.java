package top.zerotop.websocket;

import java.io.Serializable;

public class ResMsg implements Serializable{

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResMsg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

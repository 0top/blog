package top.zerotop.blog.releam;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomToken extends UsernamePasswordToken {

    private String logintype;

    public CustomToken(final String username, final String password, String logintype){
        super(username, password);
        this.logintype = logintype;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }
}

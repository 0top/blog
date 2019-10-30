package top.zerotop.blog.releam;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomToken extends UsernamePasswordToken {
    private String userRoleType;

    public CustomToken(final String username, final String password, String userRoleType) {
        super(username, password);
        this.userRoleType = userRoleType;
    }

    public String getUserRoleType() {
        return userRoleType;
    }

    public void setUserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }
}

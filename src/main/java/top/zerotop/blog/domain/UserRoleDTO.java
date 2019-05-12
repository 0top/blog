package top.zerotop.blog.domain;

import java.io.Serializable;
import java.util.Date;

public class UserRoleDTO implements Serializable{
    private Long roleId;

    private Long userId;

    private String roleName;

    private String gmtModified;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
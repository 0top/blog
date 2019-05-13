package top.zerotop.blog.db.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable{
    private long id;

    private long roleId;

    private long userId;

    private String roleName;

    private String gmtCreate;

    private String gmtModified;

    public UserRole(String roleName, String gmtCreate, String gmtModified) {
        this.roleName = roleName;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public UserRole() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "userRole{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}

package top.zerotop.blog.data.model;

/**
 * Created by:zerotop  date:2019/5/13
 */
public class RolePermission {
    private int id;
    private int roleId;
    private int permissionId;
    private String gmtCreate;

    public RolePermission() {

    }

    public RolePermission(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}

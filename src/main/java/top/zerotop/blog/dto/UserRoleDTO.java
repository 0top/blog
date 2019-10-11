package top.zerotop.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Set;

@ApiModel(value = "用户角色")
public class UserRoleDTO implements Serializable{
    @ApiModelProperty(value = "角色Id", position = 1)
    private Long roleId;

    @ApiModelProperty(value = "用户Id", position = 1)
    private Long userId;

    @ApiModelProperty(value = "角色名", position = 1)
    private String roleName;

    @ApiModelProperty(value = "修改时间", position = 1)
    private String gmtModified;

    @ApiModelProperty(value = "角色id", position = 1)
    private Set<String> permissions;

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

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
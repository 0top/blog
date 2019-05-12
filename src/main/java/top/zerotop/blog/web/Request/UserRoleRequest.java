package top.zerotop.blog.web.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by:zerotop  date:2019/5/12
 */
@ApiModel(value = "用户角色model")
public class UserRoleRequest {
    @ApiModelProperty(value = "角色id", position = 0)
    private Long roleId;

    @ApiModelProperty(value = "角色id", position = 1)
    private Long userId;

    @ApiModelProperty(value = "角色名", position = 2)
    private String roleName;

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
}

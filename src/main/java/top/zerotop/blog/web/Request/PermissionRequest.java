package top.zerotop.blog.web.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by:zerotop  date:2019/5/13
 */
@ApiModel(value = "权限")
public class PermissionRequest {
    @ApiModelProperty(value = "权限名")
    private String name;

    @ApiModelProperty(value = "权限code")
    private String code;

    @ApiModelProperty(value = "角色id")
    private int roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

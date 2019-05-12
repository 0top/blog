package top.zerotop.blog.web.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by:zerotop  date:2019/5/12
 */
@ApiModel(value = "管理员")
public class AdminRequest {
    @ApiModelProperty(value = "昵称", position = 0)
    private String nickname;

    @ApiModelProperty(value = "用户名", position = 1)
    private String username;

    @ApiModelProperty(value = "密码", position = 2)
    private String password;

    @ApiModelProperty(value = "头像地址", position = 3)
    private String avatar;

    @ApiModelProperty(value = "描述", position = 4)
    private String description;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package top.zerotop.blog.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.util.Result;
import top.zerotop.exception.AdminLoginException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月31日下午11:29:14
 */
@Api(value = "用户请求接口")
@RestController
@RequestMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @ApiOperation(value = "管理员登录", notes = "只有管理员能登录")
    @PostMapping(value = "/login")
    public Result adminLogin(@ApiParam(value = "登录时提供信息")
                             @RequestBody Admin admin,
                             HttpServletRequest req) throws AdminLoginException {

        if (null == admin) {
            throw new AdminLoginException("无法登录");
        }

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated() == true && subject.getPrincipal().equals(admin.getUsername())) {
            return new Result("当前用户已登录", null);
        }

        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            token.setRememberMe(true);
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return new Result("用户名或密码错误", null);
        } catch (UnknownAccountException uae) {
            return new Result(1, "用户名或密码错误", null);
        } catch (ExcessiveAttemptsException eae) {
            return new Result(1, "请稍后尝试", null);
        }
        req.getSession().setAttribute("user", admin);

        return new Result(0, "登录成功", null);
    }

    /**
     * 管理员登出
     * @return
     */
    @ApiOperation(value = "管理员登出", notes = "管理员登出接口")
    @GetMapping(value = "/logout")
    public Result adminLoginOut() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() == true) {
            subject.logout();
        }
        return new Result(0, "退出登录成功", null);
    }

}

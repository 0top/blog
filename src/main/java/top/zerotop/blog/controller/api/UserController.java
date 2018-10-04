package top.zerotop.blog.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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

import top.zerotop.blog.domain.Admin;
import top.zerotop.blog.util.ReMap;
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
    public String adminLogin(HttpServletRequest req,
                             @ApiParam(value = "登录时提供信息")
                             @RequestBody Admin admin) throws AdminLoginException {

        if (null == admin) {
            throw new AdminLoginException("无法登录");
        }

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated() == true && subject.getPrincipal().equals(admin.getUsername())) {
            return ReMap.ResultMap(0, "当前用户已登录", null);
        }

        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            token.setRememberMe(true);
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return ReMap.ResultMap(1, "用户名或密码错误", null);
        } catch (UnknownAccountException uae) {
            return ReMap.ResultMap(1, "用户名或密码错误", null);
        } catch (ExcessiveAttemptsException eae) {
            return ReMap.ResultMap(1, "请稍后尝试", null);
        }
        req.getSession().setAttribute("user", admin);


        return ReMap.ResultMap(0, "登录成功", null);
    }

    /**
     * 管理员登出
     *
     * @return
     */
    @ApiOperation(value = "管理员登出", notes = "管理员登出接口")
    @GetMapping(value = "/logout")
    public @ResponseBody
    String adminLoginOut() {

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated() == true) {
            subject.logout();
        }

        return ReMap.ResultMap(0, "退出登录成功", null);
    }

}

package top.zerotop.blog.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import top.zerotop.blog.db.model.Admin;
import top.zerotop.blog.releam.CustomToken;
import top.zerotop.blog.service.UserService;
import top.zerotop.blog.util.Result;
import top.zerotop.exception.AdminLoginException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月31日下午11:29:14
 */
@Api(value = "用户请求接口")
@RestController
@RequestMapping(value = "/account", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class AccountController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册管理员", notes = "管理员注册")
    @PostMapping(value = "/user/insert")
    public Result insertAdmin(@ApiParam(value = "注册时提供信息")
                              @RequestBody Admin admin) {
        Assert.notNull(admin, "添加信息不能为空");

        userService.insertAdmin(admin);
        return new Result();
    }

    @ApiOperation(value = "通过用户名和密码获取用户")
    @PostMapping(value = "/user/select")
    public Result selectAdmin(@ApiParam(value = "登录时提供用户名")
                              @RequestParam String username,
                              @ApiParam(value = "登录时提供密码")
                              @RequestParam String password) {

        Subject subject = SecurityUtils.getSubject();

        subject.checkRole("admin");
        subject.hasRole("admin");

        Admin admin = userService.selectAdmin(username);
//        Admin admin = userService.selectByUsernameAndPassword(username, password);
        return new Result(admin);
    }


    @ApiOperation(value = "管理员登录", notes = "只有管理员能登录")
    @PostMapping(value = "/login")
    public Result adminLogin(@ApiParam(value = "登录时提供用户名")
                             @RequestParam String username,
                             @ApiParam(value = "登录时提供密码")
                             @RequestParam String password,
                             HttpServletRequest req) throws AdminLoginException {

        Assert.isTrue(null != username && !"".equals(username), "用户名不能为空");
        Assert.isTrue(null != password && !"".equals(password), "密码不能为空");

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated() == true && subject.getPrincipal().equals(username)) {
            return new Result("当前用户已登录", null);
        }

        System.out.println(username + " : " + password);
        CustomToken token = new CustomToken(username, password, "Admin");
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
        req.getSession().setAttribute("user", username);

        return new Result(200, "登录成功", null);
    }

    /**
     * 管理员登出
     *
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

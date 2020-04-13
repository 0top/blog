package top.zerotop.blog.service;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import top.zerotop.blog.data.model.BlogAdmin;
import top.zerotop.blog.web.Request.AdminRequest;
import top.zerotop.global.exception.BlogException;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年6月5日下午11:00:32
 */
public interface UserService {
    BlogAdmin selectByUsernameAndPassword(String username, String password);

    @RequiresRoles("admin:article:select")
    BlogAdmin selectAdminByUserName(String username);

    @RequiresAuthentication
    int insertAdmin(AdminRequest adminRequest) throws BlogException;
}

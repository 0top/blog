package top.zerotop.blog.service;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import top.zerotop.blog.domain.UserRoleDTO;
import top.zerotop.blog.web.Request.UserRoleRequest;

import java.util.List;

public interface UserRoleService {
    int insertUserRole(UserRoleRequest userRoleRequest);

    List<UserRoleDTO> listUserRole(long userId);

    int deleteUserRole(long id);

    int insertRole(String roleName);

    @RequiresRoles("admin:article:select")
//    @RequiresPermissions("admin:role")
    List<UserRoleDTO> listRole();
}

package top.zerotop.blog.service;

import org.apache.shiro.authz.annotation.RequiresRoles;
import top.zerotop.blog.db.model.UserRole;
import top.zerotop.blog.domain.UserRoleDTO;

import java.util.List;

public interface UserRoleService {
    int insertUserRole(UserRoleDTO userRoleDTO);

    List<UserRoleDTO> listUserRole(long userId);

    int deleteUserRole(long id);

    int insertRole(String roleName);

    @RequiresRoles("admin")
    List<UserRoleDTO> listRole();
}

package top.zerotop.blog.service;

import top.zerotop.blog.db.model.UserRole;
import top.zerotop.blog.domain.UserRoleDTO;

import java.util.List;

public interface UserRoleService {
    int insertUserRole(UserRole userRole);

    List<UserRoleDTO> listUserRole(long userId);

    int deleteUserRole(long id);

    int insertRole(String roleName);

    List<UserRoleDTO> listRole();
}

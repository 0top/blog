package top.zerotop.blog.service;

import top.zerotop.blog.domain.UserRole;

import java.util.List;

public interface UserRoleService {

    int insertUserRole(UserRole userRole);

    List<UserRole> listUserRole(long userId);

    int deleteUserRole(long id);

    int insertRole(UserRole record);

    List<UserRole> listRole();
}

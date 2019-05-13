package top.zerotop.blog.service;

import top.zerotop.blog.domain.UserRoleDTO;
import top.zerotop.blog.web.Request.PermissionRequest;

/**
 * Created by:zerotop  date:2019/5/13
 */
public interface PermissionService {
    int addRolePermission(PermissionRequest permissionRequest);

    UserRoleDTO selectRolePermission(int roleId);
}

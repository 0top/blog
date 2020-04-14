package top.zerotop.blog.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zerotop.blog.data.mapper.PermissionMapper;
import top.zerotop.blog.data.model.Permission;
import top.zerotop.blog.data.model.RolePermission;
import top.zerotop.blog.dto.UserRoleDTO;
import top.zerotop.blog.service.PermissionService;
import top.zerotop.blog.web.Request.PermissionRequest;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by:zerotop  date:2019/5/13
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public int addRolePermission(PermissionRequest permissionRequest) {
        Permission permission = dozerBeanMapper.map(permissionRequest, Permission.class);
        permission.setGmtCreate(LocalDateTime.now().toString());
        permission.setGmtModified(LocalDateTime.now().toString());
        permissionMapper.addPermission(permission);

        Permission permission1 = permissionMapper.selectPermissionByCode(permission.getCode());

        RolePermission rolePermission = new RolePermission(permissionRequest.getRoleId(), permission1.getId());
        rolePermission.setGmtCreate(LocalDateTime.now().toString());
        permissionMapper.insertRolePermission(rolePermission);

        return 0;
    }

    @Override
    public UserRoleDTO selectRolePermission(int roleId) {
        Set<String> permissions = permissionMapper.selectPermissionByRoleId(roleId);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleId((long) roleId);
        userRoleDTO.setPermissions(permissions);

        return userRoleDTO;
    }
}

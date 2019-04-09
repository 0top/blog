package top.zerotop.blog.service.impl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.db.model.UserRole;
import top.zerotop.blog.domain.UserRoleDTO;
import top.zerotop.blog.service.UserRoleService;
import top.zerotop.blog.util.ConvertToDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public int insertUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = dozerBeanMapper.map(userRoleDTO, UserRole.class);
        userRole.setGmtCreate(LocalDateTime.now().toString());
        userRole.setGmtModified(LocalDateTime.now().toString());

        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRoleDTO> listUserRole(long userId) {
        List<UserRole> userRoles = userRoleMapper.listUserRoleByUserId(userId);

        return ConvertToDTO.convertToDTO(userRoles, UserRoleDTO.class);
    }

    @Override
    public int deleteUserRole(long id) {
        return userRoleMapper.deleteUserRole(id);
    }

    @Override
    public int insertRole(String roleName) {
        UserRole userRole = new UserRole(roleName, LocalDateTime.now().toString(), LocalDateTime.now().toString());
        return userRoleMapper.insertRole(userRole);
    }

    @Override
    @RequiresPermissions("admin:role")
    public List<UserRoleDTO> listRole() {
        List<UserRole> userRoles = userRoleMapper.listRole();
        System.out.println();
        return ConvertToDTO.convertToDTO(userRoles, UserRoleDTO.class);
    }
}

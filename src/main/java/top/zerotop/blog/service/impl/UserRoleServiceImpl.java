package top.zerotop.blog.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zerotop.blog.data.mapper.UserRoleMapper;
import top.zerotop.blog.data.model.UserRole;
import top.zerotop.blog.dto.UserRoleDTO;
import top.zerotop.blog.service.UserRoleService;
import top.zerotop.blog.web.Request.UserRoleRequest;
import top.zerotop.utils.ConvertUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public int insertUserRole(UserRoleRequest userRoleRequest) {
        UserRole userRole = dozerBeanMapper.map(userRoleRequest, UserRole.class);
        userRole.setGmtCreate(LocalDateTime.now().toString());
        userRole.setGmtModified(LocalDateTime.now().toString());

        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRoleDTO> listUserRole(long userId) {
        List<UserRole> userRoles = userRoleMapper.listUserRoleByUserId(userId);

        return ConvertUtils.convertToDTO(userRoles, UserRoleDTO.class);
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
    public List<UserRoleDTO> listRole() {
        List<UserRole> userRoles = userRoleMapper.listRole();
        return ConvertUtils.convertToDTO(userRoles, UserRoleDTO.class);
    }
}

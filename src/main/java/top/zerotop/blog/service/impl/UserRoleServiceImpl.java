package top.zerotop.blog.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.db.model.UserRole;
import top.zerotop.blog.domain.UserRoleDTO;
import top.zerotop.blog.service.UserRoleService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public int insertUserRole(UserRole userRole) {
        userRole.setGmtCreate(new Date());
        userRole.setGmtModified(new Date());
        
        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRoleDTO> listUserRole(long userId) {
        List<UserRole> userRoles = userRoleMapper.listUserRoleByUserId(userId);

        return convertObjectToDTO(userRoles, UserRoleDTO.class);
    }

    @Override
    public int deleteUserRole(long id) {
        return userRoleMapper.deleteUserRole(id);
    }

    @Override
    public int insertRole(String roleName) {
        UserRole userRole = new UserRole(roleName, new Date(), new Date());
        return userRoleMapper.insertRole(userRole);
    }

    @Override
    public List<UserRoleDTO> listRole() {
        List<UserRole> userRoles = userRoleMapper.listRole();

        return convertObjectToDTO(userRoles, UserRoleDTO.class);
    }

    public <T> List<T> convertObjectToDTO(Collection<?> collection, Class<T> tClass) {
        if (org.springframework.util.CollectionUtils.isEmpty(collection)) {
            List<T> lists = new ArrayList<>();
            collection.forEach(x -> {
                T t = dozerBeanMapper.map(x, tClass);
                lists.add(t);
            });
            return lists;
        } else {
            return new ArrayList<>();
        }
    }
}

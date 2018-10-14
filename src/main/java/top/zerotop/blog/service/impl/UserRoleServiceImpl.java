package top.zerotop.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zerotop.blog.db.mapper.UserRoleMapper;
import top.zerotop.blog.domain.UserRole;
import top.zerotop.blog.service.UserRoleService;

import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insertUserRole(UserRole userRole) {

        userRole.setGtmCreate(new Date());
        userRole.setGtmModified(new Date());

        return userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public List<UserRole> listUserRole(long userId) {

        return userRoleMapper.listUserRoleByUserId(userId);
    }

    @Override
    public int deleteUserRole(long id) {
        return userRoleMapper.deleteUserRole(id);
    }

    @Override
    public int insertRole(UserRole record) {
        record.setGtmCreate(new Date());
        record.setGtmModified(new Date());
        return userRoleMapper.insertRole(record);
    }

    @Override
    public List<UserRole> listRole() {
        return userRoleMapper.listRole();
    }
}

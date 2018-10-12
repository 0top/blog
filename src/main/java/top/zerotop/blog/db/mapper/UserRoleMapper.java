package top.zerotop.blog.db.mapper;

import java.util.List;

import top.zerotop.blog.domain.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);
    
    List<String> selectRoleNameByUserId(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}
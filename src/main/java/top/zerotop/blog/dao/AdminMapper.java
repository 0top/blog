package top.zerotop.blog.dao;

import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.entity.Admin;

public interface AdminMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);
    
    Admin selectByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
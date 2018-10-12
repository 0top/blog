package top.zerotop.blog.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import top.zerotop.blog.db.model.Admin;

@Mapper
public interface AdminMapper{

    int deleteByAdminCode(@Param("code") String code);

    int insertAdmin(@Param("admin") Admin admin);

    Admin selectByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    int updateAdminByCode(@Param("admin") Admin admin);
}
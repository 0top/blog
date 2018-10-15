package top.zerotop.blog.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.db.model.Admin;

@Mapper
public interface AdminMapper{

    int deleteByAdminCode(@Param("code") String code);

    int insertAdmin(Admin admin);

    @Select("select * from admin where username = #{username}")
    Admin selectAdmin(@Param("username") String username);

    @Select("select * from admin where username = #{username} and password = #{password} ")
    Admin selectByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    int updateAdminByCode(@Param("admin") Admin admin);
}
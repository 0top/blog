package top.zerotop.blog.data.mapper;

import org.apache.ibatis.annotations.*;

import top.zerotop.blog.data.model.Admin;

@Mapper
public interface AdminMapper {
    @Delete("delete from admin where code = #{code}")
    int deleteByAdminCode(@Param("code") String code);

    @Insert("insert int admin(code, nickname, username, password, avatar, description, gmt_create, gmt_modified)" +
            "values(#{code}, #{nickname}, #{username}, #{password}, #{avatar}, #{description}, #{gmtCreate}, #{gmtModified})")
    int insertAdmin(Admin admin);

    @Select("select * from admin where username = #{username}")
    Admin selectAdmin(@Param("username") String username);

    @Select("select * from admin where username = #{username} and password = #{password} ")
    Admin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("<script>" +
            "update admin" +
            "<set>" +
            "<if test='nickname!=null'>nickname = #{nickname}, </if>" +
            "<if test='avatar!=null'>avatar = #{avatar}, </if>" +
            "<if test='description!=null'>description = #{description}, </if>" +
            "<if test='nickname!=null'>nickname = #{nickname}, </if>" +
            "gmt_modified = #{gmtModified}" +
            "</set>" +
            "where code=#{code}" +
            "</script>")
    int updateAdminByCode(@Param("admin") Admin admin);
}
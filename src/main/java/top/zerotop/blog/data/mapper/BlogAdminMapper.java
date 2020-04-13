package top.zerotop.blog.data.mapper;

import org.apache.ibatis.annotations.*;

import top.zerotop.blog.data.model.BlogAdmin;

@Mapper
public interface BlogAdminMapper {
    @Update("UPDATE `blog_admin` SET `del_flag`='1' WHERE `code`=#{code}")
    int deleteByAdminCode(@Param("code") String code);

    @Insert("INSERT INTO `blog_admin` (`code`, `nickname`, `username`, `password`, `avatar`, `description`, `gmt_create`, `gmt_modified`) " +
            "values(#{code}, #{nickname}, #{username}, #{password}, #{avatar}, #{description}, #{gmtCreate}, #{gmtModified})")
    int insertBlogAdmin(BlogAdmin blogAdmin);

    @Select("SELECT * FROM `blog_admin` WHERE `username`=#{username}")
    BlogAdmin selectAdminByUserName(@Param("username") String username);

    @Select("SELECT * FROM `blog_admin` WHERE `username`=#{username} and `password`=#{password} ")
    BlogAdmin selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("<SCRIPT>" +
            "UPDATE `blog_admin` " +
            "<SET>" +
            "<if test='nickname!=null'>nickname = #{nickname}, </if>" +
            "<if test='avatar!=null'>avatar = #{avatar}, </if>" +
            "<if test='description!=null'>description = #{description}, </if>" +
            "<if test='nickname!=null'>nickname = #{nickname}, </if>" +
            "gmt_modified = #{gmtModified} " +
            "</SET>" +
            "WHERE `code`=#{code}" +
            "</SCRIPT>")
    int updateAdminByCode(@Param("admin") BlogAdmin blogAdmin);
}
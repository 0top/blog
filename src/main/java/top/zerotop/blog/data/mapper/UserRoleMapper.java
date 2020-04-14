package top.zerotop.blog.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.data.model.UserRole;

public interface UserRoleMapper {
    @Insert("INSERT INTO `user_role`(user_id, role_id, gmt_create, gmt_modified) " +
            "VALUES" +
            "(#{userId}, #{roleId}, #{gmtCreate}, #{gmtModified})")
    int insertUserRole(UserRole userRole);

    @Select("SELECT * FROM role, user_role " +
            "WHERE role.id = user_role.role_id " +
            "AND user_role.user_id=#{userId}")
    List<UserRole> listUserRoleByUserId(@Param("userId") long userId);

    @Delete("DELETE FROM `user_role` WHERE id=#{id}")
    int deleteUserRole(long id);

    @Insert("INSERT INTO `role`(role_name, gmt_create, gmt_modified) " +
            "VALUES" +
            "(#{role.roleName}, #{role.gmtCreate}, #{role.gmtModified})")
    int insertRole(@Param("role") UserRole role);

    @Select("SELECT * FROM `role`")
    List<UserRole> listRole();
}
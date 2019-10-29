package top.zerotop.blog.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.data.model.UserRole;

public interface UserRoleMapper {
    @Insert("insert into user_role(user_id, role_id, gmt_create, gmt_modified) " +
            "values(#{userId}, #{roleId}, #{gmtCreate}, #{gmtModified})")
    int insertUserRole(UserRole userRole);

    @Select("select * from role r, user_role ur " +
            "where r.id = ur.role_id " +
            "and ur.user_id = #{userId}")
    List<UserRole> listUserRoleByUserId(@Param("userId") long userId);

    @Delete("delete from user_role where id = #{id}")
    int deleteUserRole(long id);

    @Insert("insert into role(role_name, gmt_create, gmt_modified) " +
            "values(#{role.roleName}, #{role.gmtCreate}, #{role.gmtModified})")
    int insertRole(@Param("role") UserRole role);

    @Select("select * from role ")
    List<UserRole> listRole();
}
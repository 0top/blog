package top.zerotop.blog.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.data.model.Permission;
import top.zerotop.blog.data.model.RolePermission;

import java.util.Set;

@Mapper
public interface PermissionMapper {
    @Select("SELECT perm.name FROM `permission` perm, `role_permission` role_perm " +
            "WHERE role_perm.permission_id=perm.id AND role_perm.role_id=#{roleId} ")
    Set<String> selectPermissionByRoleId(int roleId);

    @Insert("INSERT INTO `permission`(name, code, gmt_create, gmt_modified) " +
            "VALUES" +
            "(#{name}, #{code}, #{gmtCreate}, #{gmtModified})")
    int addPermission(Permission permission);

    @Select("SELECT * FROM `permission` WHERE `code`=#{code}")
    Permission selectPermissionByCode(String code);

    @Insert("INSERT INTO `role_permission`(role_id, permission_id, gmt_create) " +
            "VALUES" +
            "(#{roleId}, #{permissionId}, #{gmtCreate})")
    int insertRolePermission(RolePermission rolePermission);
}

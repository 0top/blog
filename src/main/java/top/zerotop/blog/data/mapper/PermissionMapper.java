package top.zerotop.blog.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.zerotop.blog.data.model.Permission;
import top.zerotop.blog.data.model.RolePermission;

import java.util.Set;

@Mapper
public interface PermissionMapper {
    @Select("select p.name from permission p, role_permission rp " +
            "where rp.permission_id = p.id and rp.role_id = #{roleId} ")
    Set<String> selectPermissionByRoleId(int roleId);

    @Insert("insert into permission(name, code, gmt_create, gmt_modified) " +
            "values(#{name}, #{code}, #{gmtCreate}, #{gmtModified})")
    int insertPermission(Permission permission);

    @Select("select * from permission where code = #{code}")
    Permission selectPermissionByCode(String code);

    @Insert("insert into role_permission(role_id, permission_id, gmt_create) " +
            "values(#{roleId}, #{permissionId}, #{gmtCreate})")
    int insertRolePermission(RolePermission rolePermission);
}

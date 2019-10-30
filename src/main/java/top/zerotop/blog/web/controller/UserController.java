package top.zerotop.blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.zerotop.blog.dto.UserRoleDTO;
import top.zerotop.blog.service.UserRoleService;
import top.zerotop.blog.web.Request.UserRoleRequest;
import top.zerotop.utils.ServiceResult;

import java.util.List;

@Api(value = "用户权限相关", description = "用户权限相关api")
@RestController
@RequestMapping(value = "/api/admin/v1/user", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "给用户更加权限", notes = "为用户增加权限")
    @PostMapping(value = "/userRole")
    public ServiceResult insertUserRole(@ApiParam(value = "用户权限")
                                 @RequestBody UserRoleRequest userRoleRequest) {
        userRoleService.insertUserRole(userRoleRequest);
        return new ServiceResult();
    }

    @ApiOperation(value = "查看当前用户所有权限", notes = "查看当前用户所有权限")
    @GetMapping(value = "/userRole")
    public ServiceResult listUserRole(long userId) {
        return ServiceResult.make(userRoleService.listUserRole(userId));
    }

    @ApiOperation(value = "删除用户权限", notes = "删除用户权限")
    @DeleteMapping(value = "/userRole/{id}")
    public ServiceResult deleteUserRole(@ApiParam(value = "权限id")
                                 @PathVariable("id") Long id) {
        Assert.notNull(id, "id不可为空");

        userRoleService.deleteUserRole(id);
        return new ServiceResult();
    }

    @RequiresRoles("admin")
    @ApiOperation(value = "添加权限管理", notes = "添加权限")
    @PostMapping(value = "/role")
    public ServiceResult insertRole(@ApiParam(value = "权限")
                             @RequestParam("roleName") String roleName) {
        Assert.notNull(roleName, "权限值不可为空");
        userRoleService.insertRole(roleName);
        return ServiceResult.SUCCESS;
    }

    @ApiOperation(value = "列出所有权限", notes = "列出所有权限")
    @GetMapping(value = "/role")
    public ServiceResult<List<UserRoleDTO>> listRole() {
        List<UserRoleDTO> list = userRoleService.listRole();
        return ServiceResult.make(list);
//        try {
//            List<UserRoleDTO> list = userRoleService.listRole();
//            return Result.make(list);
//        } catch (UnauthenticatedException ue) {
//            System.out.println("un authenticated ...");
//            return Result.error(50003, "请登录后认证");
//        }
    }
}

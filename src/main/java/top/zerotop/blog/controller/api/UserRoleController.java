package top.zerotop.blog.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.zerotop.blog.domain.UserRole;
import top.zerotop.blog.service.UserRoleService;
import top.zerotop.blog.util.Result;

import java.util.List;

@Api(value = "用户权限相关", description = "用户权限相关api")
@RestController
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "给用户更加权限", notes = "为用户增加权限")
    @PostMapping(value = "/userRole")
    public Result insertUserRole(@ApiParam(value = "用户权限")
                                 @RequestBody UserRole userRole) {
        userRoleService.insertUserRole(userRole);
        return new Result();
    }

    @ApiOperation(value = "查看当前用户所有权限", notes = "查看当前用户所有权限")
    @GetMapping(value = "/userRole")
    public Result listUserRole(long userId) {
        return new Result(userRoleService.listUserRole(userId));
    }

    @ApiOperation(value = "删除用户权限", notes = "删除用户权限")
    @DeleteMapping(value = "/userRole/{id}")
    public Result deleteUserRole(@ApiParam(value = "权限id")
                                 @PathVariable("id") long id) {
        userRoleService.deleteUserRole(id);
        return new Result();
    }

    @ApiOperation(value = "添加权限管理", notes = "添加权限")
    @PostMapping(value = "/role")
    public Result insertRole(@ApiParam(value = "权限")
                             @RequestBody UserRole userRole) {
        userRoleService.insertRole(userRole);
        return new Result();
    }

    @ApiOperation(value = "列出所有权限", notes = "列出所有权限")
    @GetMapping(value = "/role")
    public Result listRole() {
        return new Result(userRoleService.listRole());
    }
}

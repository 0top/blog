package top.zerotop.blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.blog.service.PermissionService;
import top.zerotop.blog.web.Request.PermissionRequest;
import top.zerotop.global.exception.BlogException;
import top.zerotop.utils.ServiceResult;

/**
 * Created by:zerotop  date:2019/5/13
 */
@Api(value = "角色权限接口",description = "角色权限接口")
@RestController
@RequestMapping(value = "/api/admin/v1/permission", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @ApiOperation(value = "添加角色权限", notes = "添加角色权限")
    @PostMapping(value = "/permission")
    public ServiceResult insertAdmin(@ApiParam(value = "角色权限")
                              @RequestBody PermissionRequest permissionRequest) throws BlogException {
        Assert.notNull(permissionRequest, "添加信息不能为空");

        permissionService.addRolePermission(permissionRequest);
        return ServiceResult.SUCCESS;
    }

    @ApiOperation(value = "获取角色权限", notes = "获取")
    @PostMapping(value = "/permission/query")
    public ServiceResult insertAdmin(@ApiParam(value = "角色权限")
                              @RequestBody Integer roleId) throws BlogException {
        Assert.notNull(roleId, "id信息不能为空");

        return ServiceResult.make(permissionService.selectRolePermission(roleId));
    }
}

package com.abc.service.impl;

import com.abc.util.Result;
import com.abc.mapper.PermissionMapper;
import com.abc.model.Permission;
import com.abc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result<Permission> addPermission(Permission permission){
        Result<Permission> result = new Result<>();
        int count = permissionMapper.add(permission);
        if (count > 0) {
            result.setResultSuccess("权限创建成功", permission);
        } else {
            result.setResultFailed("权限创建失败");
        }
        return result;
    }

    @Override
    public Result<Permission> updatePermission(Permission permission){
        Result<Permission> result = new Result<>();
        int count = permissionMapper.add(permission);
        if (count > 0) {
            result.setResultSuccess("权限更改成功", permission);
        } else {
            result.setResultFailed("权限更改失败");
        }
        return result;
    }

    @Override
    public Result<Boolean> deletePermission(Integer id){
        Result<Boolean> result = new Result<>();
        int count = permissionMapper.delete(id);
        if (count > 0) {
            result.setResultSuccess("权限删除成功", true);
        } else {
            result.setResultFailed("权限删除失败");
        }
        return result;
    }

    @Override
    public Result<Permission> getPermissionById(Integer id){
        Permission permission = permissionMapper.getById(id);
        Result<Permission> result = new Result<>();
        if (id != null) {
            result.setResultSuccess("找到权限", permission);
        } else {
            result.setResultFailed("未找到权限");
        }
        return result;
    }

    @Override
    public Result<Permission> getByPermissionCode(String permissionCode){
        Permission permission = permissionMapper.getByPermissionCode(permissionCode);
        Result<Permission> result = new Result<>();
        if (permission != null) {
            result.setResultSuccess("找到权限", permission);
        } else {
            result.setResultFailed("未找到权限");
        }
        return result;
    }

    @Override
    public Result<List<Permission>> getAllPermission(){
        List<Permission> permissions = permissionMapper.getAllPermission();
        Result<List<Permission>> result = new Result<>();
        if (permissions != null && !permissions.isEmpty()) {
            result.setResultSuccess("找到权限列表", permissions);
        } else {
            result.setResultFailed("未找到权限列表");
        }
        return result;
    }
}

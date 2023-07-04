package com.abc.service.impl;


import com.abc.util.Result;
import com.abc.mapper.RolePermissionMapper;
import com.abc.model.RolePermission;
import com.abc.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Result<RolePermission> addRolePermission(RolePermission rolePermission){
        Result<RolePermission> result = new Result<>();
        int count = rolePermissionMapper.add(rolePermission);
        if (count > 0) {
            result.setResultSuccess("添加角色权限关系成功", rolePermission);
        } else {
            result.setResultFailed("添加角色权限关系失败");
        }
        return result;
    }

    @Override
    public Result<RolePermission> deleteRolePermission(Integer id){
        Result<RolePermission> result = new Result<>();
        int count = rolePermissionMapper.delete(id);
        if (count > 0) {
            result.setResultSuccess("删除角色权限关系成功", null);
        } else {
            result.setResultFailed("删除角色权限关系失败");
        }
        return result;
    }

    @Override
    public Result<RolePermission> updateRolePermission(RolePermission rolePermission){
        Result<RolePermission> result = new Result<>();
        int count = rolePermissionMapper.update(rolePermission);
        if (count > 0) {
            result.setResultSuccess("更新角色权限关系成功", rolePermission);
        } else {
            result.setResultFailed("更新角色权限关系失败");
        }
        return result;
    }

    @Override
    public Result<RolePermission> getRolePermissionByRoleId(Integer roleId){
        RolePermission rolePermission = rolePermissionMapper.getByRoleId(roleId);
        Result<RolePermission> result = new Result<>();
        if (rolePermission != null) {
            result.setResultSuccess("找到对应的角色权限关系", rolePermission);
        } else {
            result.setResultFailed("未找到对应的角色权限关系");
        }
        return result;
    }

    @Override
    public Result<RolePermission> getRolePermissionByPermissionId(Integer permissionId){
        RolePermission rolePermission = rolePermissionMapper.getByPermissionId(permissionId);
        Result<RolePermission> result = new Result<>();
        if (rolePermission != null) {
            result.setResultSuccess("找到对应的角色权限关系", rolePermission);
        } else {
            result.setResultFailed("未找到对应的角色权限关系");
        }
        return result;
    }

    @Override
    public Result<Void> assignPermissionToRole(Integer roleId, Integer permissionId){
        Result<Void> result = new Result<>();
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        int count = rolePermissionMapper.add(rolePermission);
        if (count > 0) {
            result.setResultSuccess("角色权限关系添加成功");
        } else {
            result.setResultFailed("角色权限关系添加失败");
        }
        return result;
    }

    @Override
    public Result<Void> removePermissionFromRole(Integer roleId, Integer permissionId){
        Result<Void> result = new Result<>();
        RolePermission rolePermission = rolePermissionMapper.getByRoleIdAndPermissionId(roleId, permissionId);
        if (rolePermission != null) {
            int count = rolePermissionMapper.delete(rolePermission.getId());
            if (count > 0) {
                result.setResultSuccess("角色权限关系删除成功");
            } else {
                result.setResultFailed("角色权限关系删除失败");
            }
        } else {
            result.setResultFailed("角色权限关系不存在");
        }
        return result;
    }


}

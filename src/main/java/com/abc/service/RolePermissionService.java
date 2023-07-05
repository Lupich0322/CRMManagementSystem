package com.abc.service;


import com.abc.util.Result;
import com.abc.model.RolePermission;
import org.springframework.stereotype.Service;

@Service
public interface RolePermissionService {

    Result<RolePermission> addRolePermission(RolePermission rolePermission);
    Result<RolePermission> deleteRolePermission(Integer id);
    Result<RolePermission> updateRolePermission(RolePermission rolePermission);
    Result<RolePermission> getRolePermissionByRoleId(Integer roleId);
    Result<RolePermission> getRolePermissionByPermissionId(Integer permissionId);
    Result<Void> assignPermissionToRole(Integer roleId, Integer permissionId);
    Result<Void> removePermissionFromRole(Integer roleId, Integer permissionId);

}

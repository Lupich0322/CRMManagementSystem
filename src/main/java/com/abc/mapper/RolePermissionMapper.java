package com.abc.mapper;

import com.abc.model.Role;
import com.abc.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper {
    int add(RolePermission rolePermission);
    int delete(Integer id);
    int update(RolePermission rolePermission);
    RolePermission getByRoleId(Integer roleId);
    RolePermission getByPermissionId(Integer permissionId);
    RolePermission getByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
}
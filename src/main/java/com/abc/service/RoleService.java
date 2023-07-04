package com.abc.service;

import com.abc.util.Result;
import com.abc.model.Role;

import java.util.List;

public interface RoleService {

    Result<Role> createRole(Role role);

    Result<Role> updateRole(Role role);

    Result<Boolean> deleteRole(Integer roleId);

    Result<Role> getRoleById(Integer roleId);

    Result<List<Role>> getAllRoles();

    Result<Role> getRoleByRoleCode(String roleCode);
}

package com.abc.service;

import com.abc.util.Result;
import com.abc.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Result<Role> createRole(Role role);

    Result<Role> updateRole(Role role);

    Result<Boolean> deleteRole(Integer roleId);

    Result<Role> getRoleById(Integer roleId);

    Result<List<Role>> getAllRoles();

    Result<Role> getRoleByRoleCode(String roleCode);
}

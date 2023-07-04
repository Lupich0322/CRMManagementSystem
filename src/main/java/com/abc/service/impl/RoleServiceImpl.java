package com.abc.service.impl;

import com.abc.util.Result;
import com.abc.mapper.RoleMapper;
import com.abc.model.Role;
import com.abc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result<Role> createRole(Role role) {
        Result<Role> result = new Result<>();
        int count = roleMapper.add(role);
        if (count > 0) {
            result.setResultSuccess("角色创建成功", role);
        } else {
            result.setResultFailed("角色创建失败");
        }
        return result;
    }

    @Override
    public Result<Role> updateRole(Role role) {
        Result<Role> result = new Result<>();
        int count = roleMapper.update(role);
        if (count > 0) {
            result.setResultSuccess("角色更新成功", role);
        } else {
            result.setResultFailed("角色更新失败");
        }
        return result;
    }

    @Override
    public Result<Boolean> deleteRole(Integer roleId) {
        Result<Boolean> result = new Result<>();
        int count = roleMapper.delete(roleId);
        if (count > 0) {
            result.setResultSuccess("角色删除成功", true);
        } else {
            result.setResultFailed("角色删除失败");
        }
        return result;
    }

    @Override
    public Result<Role> getRoleById(Integer roleId) {
        Role role = roleMapper.getById(roleId);
        Result<Role> result = new Result<>();
        if (role != null) {
            result.setResultSuccess("找到角色", role);
        } else {
            result.setResultFailed("未找到角色");
        }
        return result;
    }

    @Override
    public Result<List<Role>> getAllRoles() {
        List<Role> roles = roleMapper.getAllRoles();
        Result<List<Role>> result = new Result<>();
        if (roles != null && !roles.isEmpty()) {
            result.setResultSuccess("找到角色列表", roles);
        } else {
            result.setResultFailed("未找到角色列表");
        }
        return result;
    }

    @Override
    public Result<Role> getRoleByRoleCode(String roleCode) {
        Result<Role> result = new Result<>();
        Role role = roleMapper.getByRoleCode(roleCode);
        if (role != null) {
            result.setResultSuccess("找到角色", role);
        } else {
            result.setResultFailed("角色未找到");
        }
        return result;
    }

}

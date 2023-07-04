package com.abc.controller;

import com.abc.util.Result;
import com.abc.model.Role;
import com.abc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public Result<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/updateRole")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    public Result<Boolean> deleteRole(@PathVariable("id") Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/getRoleById/{id}")
    public Result<Role> getRoleById(@PathVariable("id") Integer roleId) {
        return roleService.getRoleById(roleId);
    }

    @GetMapping("/getAllRoles")
    public Result<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/getRoleByRoleCode/{roleCode}")
    public Result<Role> getRoleByRoleCode(@PathVariable("roleCode") String roleCode) {
        return roleService.getRoleByRoleCode(roleCode);
    }
}

package com.abc.controller;

import com.abc.util.Result;
import com.abc.model.RolePermission;
import com.abc.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/addRolePermission")
    public Result<RolePermission> addRolePermission(@RequestBody RolePermission rolePermission) {
        return rolePermissionService.addRolePermission(rolePermission);
    }

    @DeleteMapping("/deleteRolePermission/{id}")
    public Result<RolePermission> deleteRolePermission(@PathVariable Integer id) {
        return rolePermissionService.deleteRolePermission(id);
    }

    @PutMapping("/updateRolePermission")
    public Result<RolePermission> updateRolePermission(@RequestBody RolePermission rolePermission) {
        return rolePermissionService.updateRolePermission(rolePermission);
    }

    @GetMapping("/getRolePermissionByRoleId/{roleId}")
    public Result<RolePermission> getRolePermissionByRoleId(@PathVariable Integer roleId) {
        return rolePermissionService.getRolePermissionByRoleId(roleId);
    }

    @GetMapping("/getRolePermissionByPermissionId/{permissionId}")
    public Result<RolePermission> getRolePermissionByPermissionId(@PathVariable Integer permissionId) {
        return rolePermissionService.getRolePermissionByPermissionId(permissionId);
    }

    @PostMapping("/assignPermissionToRole/{roleId}/{permissionId}")
    public Result<Void> assignPermissionToRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
        return rolePermissionService.assignPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/removePermissionFromRole/{roleId}/{permissionId}")
    public Result<Void> removePermissionFromRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
        return rolePermissionService.removePermissionFromRole(roleId, permissionId);
    }
}

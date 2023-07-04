package com.abc.controller;

import com.abc.util.Result;
import com.abc.model.Permission;
import com.abc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/addPermission")
    public Result<Permission> addPermission(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }

    @PutMapping("/updatePermission")
    public Result<Permission> updatePermission(@RequestBody Permission permission){
        return permissionService.updatePermission(permission);
    }

    @DeleteMapping("/deletePermission/{id}")
    public Result<Boolean> deletePermission(@PathVariable Integer id){
        return permissionService.deletePermission(id);
    }

    @GetMapping("/getPermissionById/{id}")
    public Result<Permission> getPermissionById(@PathVariable Integer id){
        return permissionService.getPermissionById(id);
    }

    @GetMapping("/getByPermissionCode/{permissionCode}")
    public Result<Permission> getByPermissionCode(@PathVariable String permissionCode){
        return permissionService.getByPermissionCode(permissionCode);
    }

    @GetMapping("/getAllPermission")
    public Result<List<Permission>> getAllPermission(){
        return permissionService.getAllPermission();
    }
}

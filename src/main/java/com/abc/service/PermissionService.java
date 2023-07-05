package com.abc.service;

import com.abc.util.Result;
import com.abc.model.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {

    Result<Permission> addPermission(Permission permission);

    Result<Permission> updatePermission(Permission permission);

    Result<Boolean> deletePermission(Integer id);

    Result<Permission> getPermissionById(Integer id);

    Result<Permission> getByPermissionCode(String permissionCode);

    Result<List<Permission>> getAllPermission();
}

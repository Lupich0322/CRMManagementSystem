package com.abc.mapper;

import com.abc.model.Permission;
import com.abc.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    int add(Permission permission);

    int update(Permission permission);

    int delete(Integer id);

    Permission getById(Integer id);

    Permission getByPermissionCode(String permissionCode);

    List<Permission> getAllPermission();
}
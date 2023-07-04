package com.abc.mapper;

import com.abc.model.Permission;
import com.abc.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role getRoleById(Integer id);

    List<Permission> getPermissionsByRoleId(Integer roleId);
}
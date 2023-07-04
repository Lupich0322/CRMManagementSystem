package com.abc.mapper;

import com.abc.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int add(Role role);

    int update(Role role);

    int delete(Integer id);

    Role getById(Integer id);

    Role getByRoleCode(String roleCode);

    List<Role> getAllRoles();
}

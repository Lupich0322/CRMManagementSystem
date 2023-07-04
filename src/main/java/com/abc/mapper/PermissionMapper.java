package com.abc.mapper;

import com.abc.model.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {

    Permission getPermissionById(Integer id);
}
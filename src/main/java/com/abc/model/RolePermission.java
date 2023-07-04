package com.abc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RolePermission implements Serializable {

    /**
     * 角色权限关联id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;
}

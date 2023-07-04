package com.abc.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.security.Permission;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    @NotEmpty(message = "角色名称不能为空！")
    private String roleName;

    /**
     * 角色的权限集合
     */
    private List<Permission> permissions;
}
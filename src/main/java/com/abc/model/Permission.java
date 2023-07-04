package com.abc.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Permission implements Serializable {

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限编码
     */
    @NotEmpty(message = "权限编码不能为空！")
    private String permissionCode;

    /**
     * 权限名称
     */
    @NotEmpty(message = "权限名称不能为空！")
    private String permissionName;

    /**
     * 权限参数
     */
    private String permissionParam;
}
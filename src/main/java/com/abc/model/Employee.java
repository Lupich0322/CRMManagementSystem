package com.abc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 */
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class Employee implements Serializable {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 工号
     */
    private String employeeCode;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String employeeName;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空！")
    @Size(min = 8, message = "密码长度不能小于8！")
    private String employeePassword;

    /**
     * 用户的角色集合
     */
    private List<Role> roles;

    /**
     * 用户的角色名称
     */
    private String roleGroup;

}

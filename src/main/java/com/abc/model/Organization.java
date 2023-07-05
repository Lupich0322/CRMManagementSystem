package com.abc.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Organization implements Serializable {

    private int id;
    /**
     * 组织机构编码以及名称
     */
    @NotEmpty(message = "组织机构编码不能为空！")
    private String orgCode;
    @NotEmpty(message = "组织机构名称不能为空！")
    private String orgName;
    /**
     * 上级组织机构编码以及名称
     */
    private String superiorOrgCode;
    private String superiorOrgName;
    /**
     * 行政区域编码以及名称
     */
    private String regionCode;
    private String regionName;
    /**
     * 上级行政区域编码以及名称
     */
    private String superiorRegionCode;
    private String superiorRegionName;
    /**
     * 行政区域级别
     */
    private String regionLevel;
    /**
     * 组织机构类型
     */
    private String orgType;

}

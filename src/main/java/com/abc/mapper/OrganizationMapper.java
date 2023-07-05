package com.abc.mapper;


import com.abc.model.Organization;
import com.abc.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    int add(Organization organization);
    int update(Organization organization);
    int deleteByOrgCode(String orgCode);
    int deleteByOrgName(String orgName);
    Organization getById(Integer id);
    Organization getByOrgCode(String orgCode);
    Organization getByOrgName(String orgName);
    List<Organization> getByRegionCode(String regionCode);
    List<Organization> getByRegionName(String regionName);
    List<Organization> getBySuperiorRegionCode(String superiorRegionCode);
    List<Organization> getBySuperiorRegionName(String superiorRegionName);

}

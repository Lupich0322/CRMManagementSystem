package com.abc.service.impl;

import com.abc.mapper.OrganizationMapper;
import com.abc.model.Organization;
import com.abc.service.OrganizationService;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Result<Organization> createOrganization(Organization organization) {
        Result<Organization> result = new Result<>();
        try {
            organizationMapper.add(organization);
            result.setResultSuccess("组织信息添加成功", organization);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Organization> updateOrganization(Organization organization) {
        Result<Organization> result = new Result<>();
        try {
            organizationMapper.update(organization);
            result.setResultSuccess("组织信息修改成功", organization);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Boolean> deleteOrganizationByOrgCode(String orgCode) {
        Result<Boolean> result = new Result<>();
        try {
            organizationMapper.deleteByOrgCode(orgCode);
            result.setResultSuccess("组织信息删除成功", true);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Boolean> deleteOrganizationByOrgName(String orgName) {
        Result<Boolean> result = new Result<>();
        try {
            organizationMapper.deleteByOrgName(orgName);
            result.setResultSuccess("组织信息删除成功", true);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Organization> getOrganizationById(Integer id) {
        Result<Organization> result = new Result<>();
        try {
            Organization organization = organizationMapper.getById(id);
            result.setResultSuccess("组织通过id查找成功", organization);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Organization> getOrganizationByOrgCode(String orgCode) {
        Result<Organization> result = new Result<>();
        try {
            Organization organization = organizationMapper.getByOrgCode(orgCode);
            result.setResultSuccess("组织通过组织编码查找成功", organization);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Organization> getOrganizationByOrgName(String orgName) {
        Result<Organization> result = new Result<>();
        try {
            Organization organization = organizationMapper.getByOrgName(orgName);
            result.setResultSuccess("组织通过组织名称查找成功", organization);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<Organization>> getOrganizationsByRegionCode(String regionCode) {
        Result<List<Organization>> result = new Result<>();
        try {
            List<Organization> organizations = organizationMapper.getByRegionCode(regionCode);
            result.setResultSuccess("组织通过行政区域编码查找成功", organizations);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<Organization>> getOrganizationsByRegionName(String regionName) {
        Result<List<Organization>> result = new Result<>();
        try {
            List<Organization> organizations = organizationMapper.getByRegionName(regionName);
            result.setResultSuccess("组织通过行政区域名称查找成功", organizations);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<Organization>> getOrganizationsBySuperiorRegionCode(String superiorRegionCode) {
        Result<List<Organization>> result = new Result<>();
        try {
            List<Organization> organizations = organizationMapper.getBySuperiorRegionCode(superiorRegionCode);
            result.setResultSuccess("组织通过上级行政区域编码查找成功", organizations);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<Organization>> getOrganizationsBySuperiorRegionName(String superiorRegionName) {
        Result<List<Organization>> result = new Result<>();
        try {
            List<Organization> organizations = organizationMapper.getBySuperiorRegionName(superiorRegionName);
            result.setResultSuccess("组织通过上级行政区域名称查找成功", organizations);
        } catch (Exception e) {
            result.setResultFailed(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Boolean> updateSuperiorRegionCode(String orgCode, String superiorRegionCode) {
        int result = organizationMapper.updateSuperiorRegionCode(orgCode, superiorRegionCode);
        Result<Boolean> resultData = new Result<>();
        if (result > 0) {
            resultData.setResultSuccess("成功修改上级行政区域编码");
        } else {
            resultData.setResultFailed("未能成功修改上级行政编码");
        }
        return resultData;
    }

    @Override
    public Result<List<Organization>> getOrganizationsByRegionLevel(Integer regionLevel) {
        List<Organization> organizations = organizationMapper.getByRegionLevel(regionLevel);
        Result<List<Organization>> resultData = new Result<>();
        if (organizations != null) {
            resultData.setResultSuccess("按行政区域级别成功检索组织", organizations);
        } else {
            resultData.setResultFailed("按行政区域级别未能成功检索组织");
        }
        return resultData;
    }

    @Override
    public Result<Map<String, Integer>> getOrganizationCountByRegion() {
        Map<String, Integer> count = organizationMapper.countOrganizationsByRegion();
        Result<Map<String, Integer>> resultData = new Result<>();
        if (count != null) {
            resultData.setResultSuccess("已成功检索按区域划分的组织数量", count);
        } else {
            resultData.setResultFailed("未能成功检索按区域划分的组织数量");
        }
        return resultData;
    }
}

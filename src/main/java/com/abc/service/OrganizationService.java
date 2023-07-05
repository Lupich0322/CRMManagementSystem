package com.abc.service;

import com.abc.model.Organization;
import com.abc.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    Result<Organization> createOrganization(Organization organization);
    Result<Organization> updateOrganization(Organization organization);
    Result<Boolean> deleteOrganizationByOrgCode(String orgCode);
    Result<Boolean> deleteOrganizationByOrgName(String orgName);
    Result<Organization> getOrganizationById(Integer id);
    Result<Organization> getOrganizationByOrgCode(String orgCode);
    Result<Organization> getOrganizationByOrgName(String orgName);
    Result<List<Organization>> getOrganizationsByRegionCode(String regionCode);
    Result<List<Organization>> getOrganizationsByRegionName(String regionName);
    Result<List<Organization>> getOrganizationsBySuperiorRegionCode(String superiorRegionCode);
    Result<List<Organization>> getOrganizationsBySuperiorRegionName(String superiorRegionName);
}

package com.abc.controller;

import com.abc.model.Organization;
import com.abc.service.OrganizationService;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/createOrganization")
    public Result<Organization> createOrganization(@RequestBody Organization organization) {
        return organizationService.createOrganization(organization);
    }

    @PutMapping("/updateOrganization")
    public Result<Organization> updateOrganization(@RequestBody Organization organization) {
        return organizationService.updateOrganization(organization);
    }

    @DeleteMapping("/deleteOrganizationByOrgCode/{orgCode}")
    public Result<Boolean> deleteOrganizationByOrgCode(@PathVariable("orgCode") String orgCode) {
        return organizationService.deleteOrganizationByOrgCode(orgCode);
    }

    @DeleteMapping("/deleteOrganizationByOrgName/{orgName}")
    public Result<Boolean> deleteOrganizationByOrgName(@PathVariable("orgName") String orgName) {
        return organizationService.deleteOrganizationByOrgName(orgName);
    }

    @GetMapping("/getOrganizationById/{id}")
    public Result<Organization> getOrganizationById(@PathVariable("id") Integer id) {
        return organizationService.getOrganizationById(id);
    }

    @GetMapping("/getOrganizationByOrgCode/{orgCode}")
    public Result<Organization> getOrganizationByOrgCode(@PathVariable("orgCode") String orgCode) {
        return organizationService.getOrganizationByOrgCode(orgCode);
    }

    @GetMapping("/getOrganizationByOrgName/{orgName}")
    public Result<Organization> getOrganizationByOrgName(@PathVariable("orgName") String orgName) {
        return organizationService.getOrganizationByOrgName(orgName);
    }

    @GetMapping("/getOrganizationsByRegionCode/{regionCode}")
    public Result<List<Organization>> getOrganizationsByRegionCode(@PathVariable("regionCode") String regionCode) {
        return organizationService.getOrganizationsByRegionCode(regionCode);
    }

    @GetMapping("/getOrganizationsByRegionName/{regionName}")
    public Result<List<Organization>> getOrganizationsByRegionName(@PathVariable("regionName") String regionName) {
        return organizationService.getOrganizationsByRegionName(regionName);
    }

    @GetMapping("/getOrganizationsBySuperiorRegionCode/{superiorRegionCode}")
    public Result<List<Organization>> getOrganizationsBySuperiorRegionCode(@PathVariable("superiorRegionCode") String superiorRegionCode) {
        return organizationService.getOrganizationsBySuperiorRegionCode(superiorRegionCode);
    }

    @GetMapping("/getOrganizationsBySuperiorRegionName/{superiorRegionName}")
    public Result<List<Organization>> getOrganizationsBySuperiorRegionName(@PathVariable("superiorRegionName") String superiorRegionName) {
        return organizationService.getOrganizationsBySuperiorRegionName(superiorRegionName);
    }

    @PutMapping("/updateSuperiorRegionCode/{orgCode}/{superiorRegionCode}")
    public Result<Boolean> updateSuperiorRegionCode(@PathVariable("orgCode") String orgCode, @PathVariable("superiorRegionCode") String superiorRegionCode) {
        return organizationService.updateSuperiorRegionCode(orgCode, superiorRegionCode);
    }

    @GetMapping("/getOrganizationsByRegionLevel/{regionLevel}")
    public Result<List<Organization>> getOrganizationsByRegionLevel(@PathVariable("regionLevel") Integer regionLevel) {
        return organizationService.getOrganizationsByRegionLevel(regionLevel);
    }

    @GetMapping("/getOrganizationCountByRegion")
    public Result<Map<String, Integer>> getOrganizationCountByRegion() {
        return organizationService.getOrganizationCountByRegion();
    }
}

package com.abc.mapper;

import com.abc.model.Employee;
import com.abc.model.Permission;
import com.abc.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EmployeeMapper {

    /**
     * 新增用户
     *
     * @param employee 用户对象
     * @return 新增成功记录条数
     */
    int add(Employee employee);

    /**
     * 修改用户信息
     *
     * @param employee 用户对象
     * @return 修改成功记录条数
     */
    int update(Employee employee);

    /**
     * 根据使用者获取用户
     *
     * @param employeeName 使用者
     * @return 用户对象
     */
    Employee getByEmployeeName(String employeeName);

    /**
     * 根据工号获取用户
     *
     * @param employeeCode 工号
     * @return 用户对象
     */
    Employee getByEmployeeCode(Integer employeeCode);

    List<Role> getRolesByEmployeeId(Integer employeeId);

    List<Permission> getPermissionsByEmployeeId(Integer employeeId);

}

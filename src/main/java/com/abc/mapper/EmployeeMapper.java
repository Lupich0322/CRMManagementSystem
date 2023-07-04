package com.abc.mapper;

import com.abc.model.Employee;
import org.apache.ibatis.annotations.Mapper;


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
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    Employee getById(Integer id);

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
    Employee getByEmployeeCode(String employeeCode);

}

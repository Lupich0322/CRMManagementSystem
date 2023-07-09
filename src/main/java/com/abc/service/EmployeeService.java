package com.abc.service;

import com.abc.util.Result;
import com.abc.model.Employee;
import com.abc.model.Permission;
import com.abc.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    /**
     * 用户注册
     *
     * @param employee 用户对象
     * @return 注册结果
     */
    Result<Object> register(Employee employee);


    /**
     * 用户登录
     *
     * @param employee 用户对象
     * @return 登录结果
     */
    Result<Object> login(Employee employee, HttpServletRequest request);

    /**
     * 修改用户信息
     *
     * @param employee 用户对象
     * @return 修改结果
     */
    Result<Employee> update(Employee employee) throws Exception;

    /**
     * 新增用户
     *
     * @param employee 用户对象
     * @return 新增结果
     */
    Result<Employee> addEmployee(Employee employee);

    /**
     * 删除用户
     *
     * @param employeeCode 用户id
     * @return 删除结果
     */
    Result<Void> deleteEmployee(Integer employeeCode);

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
     *
     * @param session 传入请求session
     * @return 返回结果，若用户已登录则返回用户信息
     */
    Result<Object> isLogin(HttpSession session);





    /**
     * 根据工号查询用户信息
     *
     * @param employeeCode 用户对象
     * @return 查询结果
     */
    Result<Employee> getByEmployeeCode(Integer employeeCode);

    Result<Employee> getByEmployeeName(String employeeName);

    /**
     * 获取用户的所有角色
     *
     * @param employeeCode 用户id
     * @return 用户的所有角色
     */
    Result<List<Role>> getRolesByEmployeeCode(Integer employeeCode);

    /**
     * 获取用户的所有权限
     *
     * @param employeeCode 用户id
     * @return 用户的所有权限
     */
    Result<List<Permission>> getPermissionsByEmployeeCode(Integer employeeCode);

    UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException;
}

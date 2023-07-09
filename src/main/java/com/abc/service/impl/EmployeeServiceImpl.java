package com.abc.service.impl;

import com.abc.controller.EmployeeController;
import com.abc.mapper.EmployeeMapper;
import com.abc.util.Result;
import com.abc.mapper.PermissionMapper;
import com.abc.mapper.RoleMapper;
import com.abc.model.Employee;
import com.abc.model.Permission;
import com.abc.model.Role;
import com.abc.service.EmployeeService;
import com.abc.util.ClassExamine;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result<Object> register(Employee employee) {
        Result<Object> result = new Result<>();
        // 先去数据库找用户名是否存在
        Employee getEmployee = employeeMapper.getByEmployeeName(employee.getEmployeeName());
        if (getEmployee != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码
        employee.setEmployeePassword(DigestUtils.md5Hex(employee.getEmployeePassword()));
        // 存入数据库
        try {
            employeeMapper.add(employee);
        } catch (Exception e) {
            // handle the exception and return a friendly error message
            result.setResultFailed("注册失败，请稍后再试！");
            return result;
        }
        // 返回成功消息
        result.setResultSuccess("注册用户成功！", employee.getEmployeeName());
        return result;
    }


    @Override
    public Result<Object> login(Employee employee, HttpServletRequest request) {
        Result<Object> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getByEmployeeName(employee.getEmployeeName());
        if (getEmployee == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 比对密码
        if (!getEmployee.getEmployeePassword().equals(DigestUtils.md5Hex(employee.getEmployeePassword()))) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        // 设定登录成功消息，同时将用户信息存储在 session 中
        request.getSession().setAttribute(EmployeeController.SESSION_NAME, getEmployee);
        result.setResultSuccess("登录成功！", request.getSession().getId());
        return result;
    }

    @Override
    public Result<Employee> update(Employee employee) throws Exception {
        Result<Employee> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getByEmployeeCode(employee.getEmployeeCode());
        if (getEmployee == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StringUtils.isEmpty(employee.getEmployeePassword())) {
            // 加密储存
            employee.setEmployeePassword(DigestUtils.md5Hex(employee.getEmployeePassword()));
        }
        // 对象互补
        ClassExamine.objectOverlap(employee, getEmployee);
        // 存入数据库
        employeeMapper.update(employee);
        result.setResultSuccess("修改用户成功！", employee);
        return result;
    }

    @Override
    public Result<Employee> addEmployee(Employee employee) {
        Result<Employee> result = new Result<>();
        // 先去数据库找用户名是否存在
        Employee getEmployee = employeeMapper.getByEmployeeName(employee.getEmployeeName());
        if (getEmployee != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码
        employee.setEmployeePassword(DigestUtils.md5Hex(employee.getEmployeePassword()));
        // 存入数据库
        employeeMapper.add(employee);
        // 返回成功消息
        result.setResultSuccess("添加用户成功！", employee);
        return result;
    }

    @Override
    public Result<Void> deleteEmployee(Integer employeeCode) {
        Result<Void> result = new Result<>();
        int rows = employeeMapper.delete(employeeCode);
        if (rows > 0) {
            result.setResultSuccess("删除用户成功！");
        } else {
            result.setResultFailed("删除用户失败！");
        }
        return result;
    }


    @Override
    public Result<Object> isLogin(HttpSession session) {
        Result<Object> result = new Result<>();
        // 从session中取出用户信息
        Employee sessionEmployee = (Employee) session.getAttribute(EmployeeController.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionEmployee == null) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        // 登录了则返回用户已登录的消息和 sessionId
        result.setResultSuccess("用户已登录！", session.getId());
        return result;
    }


    @Override
    public Result<Employee> getByEmployeeCode(Integer employeeCode){
        Result<Employee> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getByEmployeeCode(employeeCode);
        if (getEmployee == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        result.setResultSuccess("查询成功！", getEmployee);
        return result;
    }

    @Override
    public Result<List<Role>> getRolesByEmployeeCode(Integer employeeCode) {
        Result<List<Role>> result = new Result<>();
        List<Role> roles = employeeMapper.getRolesByEmployeeCode(employeeCode);
        if (roles == null || roles.isEmpty()) {
            result.setResultFailed("该用户没有任何角色！");
            return result;
        }
        result.setResultSuccess("获取角色成功！", roles);
        return result;
    }

    @Override
    public Result<List<Permission>> getPermissionsByEmployeeCode(Integer employeeCode) {
        Result<List<Permission>> result = new Result<>();
        List<Permission> permissions = employeeMapper.getPermissionsByEmployeeCode(employeeCode);
        if (permissions == null || permissions.isEmpty()) {
            result.setResultFailed("该用户没有任何权限！");
            return result;
        }
        result.setResultSuccess("获取权限成功！", permissions);
        return result;
    }

    @Override
    public Result<Employee> getByEmployeeName(String employeeName){
        Result<Employee> result = new Result<>();
        try {
            Employee getEmployee = employeeMapper.getByEmployeeName(employeeName);
            if (getEmployee == null) {
                result.setResultFailed("该用户没有任何角色！");
            } else {
                result.setResultSuccess("获取员工成功！", getEmployee);
            }
        } catch (Exception e) {
            result.setResultFailed("获取员工出现错误：" + e.getMessage());
            // 在这里记录日志
        }
        return result;
    }


    @Override
    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
        // 在这里加载用户数据，如查询数据库获取用户信息
        Employee employee = employeeMapper.getByEmployeeName(employeeName);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee not found.");
        }

        // 转换为UserDetails对象并返回
        return User.withUsername(employee.getEmployeeName())
                .password(employee.getEmployeePassword())
                .authorities(getAuthorities(employee)) // 此处是用户的角色和权限
                .build();
    }

    // 将用户的角色和权限转换为Spring Security的GrantedAuthority集合
    private Collection<? extends GrantedAuthority> getAuthorities(Employee employee) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Result<List<Role>> rolesResult = getRolesByEmployeeCode(employee.getEmployeeCode());
        if (rolesResult.isSuccess()) {
            for (Role role : rolesResult.getData()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())); // 添加角色
            }
        }

        Result<List<Permission>> permissionsResult = getPermissionsByEmployeeCode(employee.getEmployeeCode());
        if (permissionsResult.isSuccess()) {
            for (Permission permission : permissionsResult.getData()) {
                authorities.add(new SimpleGrantedAuthority("PERM_" + permission.getPermissionName())); // 添加权限
            }
        }
        return authorities;
    }

}



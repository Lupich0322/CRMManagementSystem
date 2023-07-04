package com.abc.service.impl;

import com.abc.api.EmployeeAPI;
import com.abc.mapper.EmployeeMapper;
import com.abc.controller.Result;
import com.abc.model.Employee;
import com.abc.service.EmployeeService;
import com.abc.util.ClassExamine;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Result<Employee> register(Employee employee) {
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
        result.setResultSuccess("注册用户成功！", employee);
        return result;
    }

    @Override
    public Result<Employee> login(Employee employee) {
        Result<Employee> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getByEmployeeName(employee.getEmployeeName());
        if (getEmployee == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getEmployee.getEmployeePassword().equals(DigestUtils.md5Hex(employee.getEmployeePassword()))) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        // 设定登录成功消息
        result.setResultSuccess("登录成功！", getEmployee);
        return result;
    }

    @Override
    public Result<Employee> update(Employee employee) throws Exception {
        Result<Employee> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getById(employee.getId());
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
    public Result<Employee> isLogin(HttpSession session) {
        Result<Employee> result = new Result<>();
        // 从session中取出用户信息
        Employee sessionEmployee = (Employee) session.getAttribute(EmployeeAPI.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionEmployee == null) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        // 登录了则去数据库取出信息进行比对
        Employee getEmployee = employeeMapper.getById(sessionEmployee.getId());
        // 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
        if (getEmployee == null || !getEmployee.getEmployeePassword().equals(sessionEmployee.getEmployeePassword())) {
            result.setResultFailed("用户信息无效！");
            return result;
        }
        result.setResultSuccess("用户已登录！", getEmployee);
        return result;
    }

    @Override
    public Result<Employee> select(Employee employee){
        Result<Employee> result = new Result<>();
        // 去数据库查找用户
        Employee getEmployee = employeeMapper.getByEmployeeCode(employee.getEmployeeCode());
        if (getEmployee == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        employeeMapper.getByEmployeeCode(String.valueOf(employee));
        result.setResultSuccess("查询成功！", employee);
        return result;
    }
}



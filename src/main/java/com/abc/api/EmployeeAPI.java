package com.abc.api;

import com.abc.controller.Result;
import com.abc.model.Employee;
import com.abc.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@RestController
public class EmployeeAPI {

    /**
     * session的字段名
     */
    public static final String SESSION_NAME = "userInfo";
    private static int employeeCounter = 1;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 用户注册
     *
     * @param employee    传入注册用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<Employee> register(@RequestBody @Valid Employee employee, BindingResult errors, HttpServletRequest request) {
        Result<Employee> result = new Result<>();
        // 如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }

        // 自动生成工号，使用递增的值
        String employeeCode = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + "-" + employeeCounter;
        employee.setEmployeeCode(employeeCode);

        // 更新计数器的值
        employeeCounter++;

        // 调用注册服务
        result = employeeService.register(employee);
        return result;
    }

    /**
     * 用户登录
     *
     * @param employee    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<Employee> login(@RequestBody @Valid Employee employee, BindingResult errors, HttpServletRequest request) {
        Result<Employee> result;
        // 如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用登录服务
        result = employeeService.login(employee);
        // 如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/is-login")
    public Result<Employee> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return employeeService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param employee    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PutMapping("/employee/update")
    public Result<Employee> update(@RequestBody Employee employee, HttpServletRequest request) throws Exception {
        Result<Employee> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        Employee sessionEmployee = (Employee) session.getAttribute(SESSION_NAME);
        if (sessionEmployee.getId() != employee.getId().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = employeeService.update(employee);
        // 修改成功则刷新session信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 工号查询
     *
     * @param employee    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @GetMapping("/employee/select")
    public Result<Employee> select(@RequestBody Employee employee, HttpServletRequest request) throws Exception {
        Result<Employee> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        Employee sessionEmployee = (Employee) session.getAttribute(SESSION_NAME);
        if (sessionEmployee.getId() != employee.getId().intValue()) {
            result.setResultFailed("当前登录用户和不一致，终止！");
            return result;
        }
        result = employeeService.select(employee);
        // 修改成功则刷新session信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 工号新增
     *
     * @param employee    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PostMapping("/employee/add")

    /**
     * 工号删除
     *
     * @param employee    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @DeleteMapping("/employee/delete")



    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！");
        return result;
    }

}

package com.abc.service;

import com.abc.controller.Result;
import com.abc.model.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    /**
     * 用户注册
     *
     * @param employee 用户对象
     * @return 注册结果
     */
    Result<Employee> register(Employee employee);

    /**
     * 用户登录
     *
     * @param employee 用户对象
     * @return 登录结果
     */
    Result<Employee> login(Employee employee);

    /**
     * 修改用户信息
     *
     * @param employee 用户对象
     * @return 修改结果
     */
    Result<Employee> update(Employee employee) throws Exception;

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
     *
     * @param session 传入请求session
     * @return 返回结果，若用户已登录则返回用户信息
     */
    Result<Employee> isLogin(HttpSession session);


    /**
     * 根据工号查询用户信息
     *
     * @param employee 用户对象
     * @return 查询结果
     */
    Result<Employee> select(Employee employee);
}

package com.abc.config;

import com.abc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 表明这是一个配置类
@EnableWebSecurity  // 启用Spring Security的web安全支持
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;  // 注入UserDetailsService，用于加载用户特定的数据

    @Autowired
    private PasswordEncoder passwordEncoder;  // 注入PasswordEncoder，用于密码的加密与比对

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // DaoAuthenticationProvider是Spring Security提供的持久化的身份验证提供者
        // 使用UserDetailsService和PasswordEncoder来验证一个username/password的组合，
        // 并返回一个完全填充的UserDetails对象(如果找到的话)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // 注意：如果你正在使用Spring Boot，建议去掉此bean，并将内容移动到一个继承了WebSecurityConfigurerAdapter的类中
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 使用HttpSecurity配置安全控制
        http
                .authorizeRequests()  // 对请求进行安全配置
                .requestMatchers("/login", "/register").permitAll()  // 设置"/login", "/register"路径不需要任何安全验证
                .anyRequest().authenticated()  // 其他所有请求都需要认证后才能访问
                .and()
                .formLogin().loginPage("/login")  // 设置自定义的登录页面路径
                .and()
                .logout().logoutUrl("/logout");  // 设置登出url
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 全局用户信息，使用userDetailsService和passwordEncoder进行验证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}

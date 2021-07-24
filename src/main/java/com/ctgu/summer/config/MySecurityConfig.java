package com.ctgu.summer.config;

//import com.ctgu.summer.security.*;
//import com.ctgu.summer.service.impl.UserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @ClassName MySecurityConfig
 * @Description TODO
 * @Author wby
 * @Data 2021/7/15 23:43
 * @Version 1.0
 */
/**
@Configuration
@Slf4j
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // 用户自定义登录权限
    @Autowired
    UserDetailServiceImpl userDetailService;

    // 匿名用户登录权限
    @Autowired
    MyAuthenticationEntry myAuthenticationEntry;

    // 用户登录成功处理
    @Autowired
    MyLoginSuccessHandle myLoginSuccessHandle;

    // 用户登录失败处理
    @Autowired
    MyLoginFailureHandle myLoginFailureHandle;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    // JSON 登录成功处理
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    // JSON 登录失败处理
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    // 加密
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge((long) 3600);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    MyUsernamePasswordAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        //认证成功的处理逻辑
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        //认证失败的处理逻辑
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return filter;
    }

    // 认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭跨域和csrf防火墙
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/summer/login").permitAll()
                .antMatchers("/login").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                // 登入
                .and().formLogin().permitAll()//允许所有用户
                .successHandler(myLoginSuccessHandle)//登录成功处理逻辑
                .failureHandler(myLoginFailureHandle);//登录失败处理逻辑

        http.formLogin()
                .loginProcessingUrl("/summer/login");

        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**")
                .antMatchers("/css/**",
                        "/js/**", "/index.html",
                        "/img/**", "/fonts/**", "/favicon.ico");
    }
}

**/

package com.ctgu.summer.security;
/**
import com.ctgu.summer.entity.User;
import com.ctgu.summer.service.UserService;
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 **/
/**
 * @ClassName MyAuthenticationSuccessHandler
 * @Description JSON登录认证成功处理
 * @Author wby
 * @Data 2021/7/17 9:51
 * @Version 1.0
 */
/**
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        log.info("权限信息："+authentication);
        Result ok;
        if(authentication.getAuthorities().toString().contains("USER")){
            User user = userService.getById(authentication.getName());
            ok = ResultUtil.success("登录成功", user);
            log.info("当前登录的普通用户："+user);
        }
        else {
            User admin= userService.getById(authentication.getName());
            ok = ResultUtil.success("登录成功", admin);
            log.info("当前登录的管理员："+admin);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }
}
**/
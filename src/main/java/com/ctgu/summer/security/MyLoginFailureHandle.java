package com.ctgu.summer.security;
/**
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
**/
/**
 * @ClassName MyLoginFailureHandle
 * @Description TODO
 * @Author wby
 * @Data 2021/7/16 23:37
 * @Version 1.0
 */
/**
@Component
@Slf4j
public class MyLoginFailureHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            Result error = ResultUtil.error(ResultEnum.LOGIN_FAILED);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(error));
            out.flush();
            out.close();
    }
}
**/
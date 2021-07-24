package com.ctgu.summer.security;
/**
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @ClassName MyAuthenticationFailureHandler
 * @Description TODO
 * @Author wby
 * @Data 2021/7/17 9:54
 * @Version 1.0
 */
/**
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        Result error = ResultUtil.error(ResultEnum.LOGIN_FAILED);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
    }
}
**/
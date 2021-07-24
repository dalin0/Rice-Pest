package com.ctgu.summer.security;

//import com.ctgu.summer.utils.ResultUtil;
//import com.ctgu.summer.vo.Result;
//import com.ctgu.summer.vo.ResultEnum;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;

/**
 * @ClassName MyAuthenticationEntry
 * @Description 未登录用户接口
 * @Author wby
 * @Data 2021/7/16 23:29
 * @Version 1.0
 */
/**
@Component
@Slf4j
public class MyAuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Result error = ResultUtil.error(ResultEnum.PERMISSION_DENIED.UNAUTHORIZED);
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
    }
}
 **/

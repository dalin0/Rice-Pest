package com.ctgu.summer.security;

import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName MyAccessDeniedHandler
 * @Description TODO
 * @Author wby
 * @Data 2021/7/17 0:34
 * @Version 1.0
 */
/**
@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(403);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(ResultUtil.error(ResultEnum.PERMISSION_DENIED)));
        out.flush();
        out.close();
    }
}
**/
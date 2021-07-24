 package com.ctgu.summer.security;
/**
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
 **/
/**
 * @ClassName MyUsernamePasswordAuthenticationFilter
 * @Description TODO
 * @Author wby
 * @Data 2021/7/17 0:33
 * @Version 1.0
 */
/**
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // 覆盖父类的方法，增加 JSON 的登录方式
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if (request.getContentType().contains("json")) {
            // 说明用户以 JSON 的形式传递的参数
            String username = null;
            String password = null;
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                username = map.get("username");
                password = map.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }

        return super.attemptAuthentication(request, response);
    }
}
 **/

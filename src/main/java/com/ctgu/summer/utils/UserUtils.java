package com.ctgu.summer.utils;

import com.ctgu.summer.entity.User;
import com.ctgu.summer.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserUtils
 * @Description TODO
 * @Author wby
 * @Data 2021/7/19 9:34
 * @Version 1.0
 */
@Configuration
public class UserUtils {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    /**
     * jwt获取当前登录用户
     * @param authorization
     * @return
     */
    public User getCurrentUser(String authorization) {
        /**
         * 从请求头信息中获取数据
         * 1、获取请求头信息：名称=Authorization(前后端约定)
         * 2、替换Bearer+空格
         * 3、解析token
         * 4、获取Claims
         */

        String token = authorization.replace("Bearer ", "");
        // 3、解析token
        Claims claims = jwtUtils.parseJwt(token);
        // 4、获取claims信息
        int id = Integer.parseInt(claims.getId());
        // 根据id获取到用户
        User user = userService.getById(id);
        return user;
    }

}

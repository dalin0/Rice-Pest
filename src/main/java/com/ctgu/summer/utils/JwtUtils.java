package com.ctgu.summer.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtUtils
 * @Description jwt工具类
 * @Author wby
 * @Data 2021/7/17 23:51
 * @Version 1.0
 */
@Data
@Configuration
public class JwtUtils {
    // 签名私钥
    private String key = "userlogin";
    // 签名失效时间
    private Long failureTime = 36000000L;

    /**
     * 设置认证Token
     * @param id  用户登录id
     * @param subject 用户登录名
     */
     public String createJwt(String id, String subject, Map<String, Object> map) {
         // 设置失效时间
         long now = System.currentTimeMillis();
         long exp = now + failureTime;

         // 创建JwtBuilder
         JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(subject)
                 .setIssuedAt(new Date())
                 // 设置签名防止篡改
                 .signWith(SignatureAlgorithm.HS256, key);
         // 根据map设置claims
         for (Map.Entry<String, Object> entry:map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
         }
         jwtBuilder.setExpiration(new Date(exp));
         // 创建token
         String token = jwtBuilder.compact();
         return token;
     }

    /**
     * 解析token
     * @param token
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

}

package com.ctgu.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.summer.entity.User;
import com.ctgu.summer.mapper.UserMapper;
import com.ctgu.summer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName UserDetailServiceImpl
 * @Description 实现所有用户的权限设置
 * @Author wby
 * @Data 2021/7/16 11:13
 * @Version 1.0
 */
/**
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:"+username);
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("nickname", username);
        User dbuser = userMapper.selectOne(qw);
        log.info("查询到的用户为："+dbuser);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        org.springframework.security.core.userdetails.User user = null;
        if(dbuser != null) {
            if(dbuser.getIsAdmin().equals(1)) {
                // 管理员权限
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                log.info("当前用户为管理员"+dbuser);
            } else {
                // 普通用户权限
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                log.info("当前用户为普通用户"+dbuser);
            }
            user = new org.springframework.security.core.userdetails.User(dbuser.getId().toString(),
                    passwordEncoder.encode(dbuser.getPassword()), authorities);
            return user;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}

**/
package com.ctgu.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.summer.entity.User;
import com.ctgu.summer.mapper.UserMapper;
import com.ctgu.summer.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByNickName(String nickName) {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("nickname", nickName);
        User user = userMapper.selectOne(qw);
        return user;
    }
}

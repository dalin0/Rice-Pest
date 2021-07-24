package com.ctgu.summer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.summer.dto.AuthDTO;
import com.ctgu.summer.entity.User;
import com.ctgu.summer.service.UserService;
import com.ctgu.summer.utils.EncryptionUtils;
import com.ctgu.summer.utils.JwtUtils;
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import com.qcloud.cos.utils.Md5Utils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * @ClassName AuthController
 * @Description 登录注册
 * @Author wby
 * @Data 2021/7/16 14:00
 * @Version 1.0
 */

@RestController
@Slf4j
@RequestMapping("/summer")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody @Valid AuthDTO authDTO) {
        log.info("登录信息："+authDTO);
        User user = userService.getByNickName(authDTO.getUsername());
        log.info("用户信息："+user);
        if(user == null) {
            return ResultUtil.error(ResultEnum.LOGIN_FAILED);
        }
        System.out.println(authDTO.getPassword() + '\n' + user.getPassword());
        if (!EncryptionUtils.getSaltverifyMD5(authDTO.getPassword(), user.getPassword(), user.getSalt())) {
            return ResultUtil.error(ResultEnum.LOGIN_FAILED);
        }
        // 验证通过，生成token，并返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("isAdmin", user.getIsAdmin());
        map.put("avatar", user.getAvatar());
        String token = jwtUtils.createJwt(user.getId().toString(), user.getNickname(), map);
        log.info("生成的token串："+token);
        return ResultUtil.success("登录成功", token);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user) {
        log.info("用户信息："+user);
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("nickname", user.getNickname());
        User user1 = userService.getOne(qw);
        if (user1 != null) {
            return ResultUtil.error("昵称已经被占用");
        }
        try {
            // 随机生成盐
            String salt = UUID.randomUUID().toString();
            System.out.println("UUID:"+salt.toString() + '\n' + salt.length());
            user.setPassword(EncryptionUtils.getSaltMD5(user.getPassword(), salt));
            user.setSalt(salt);
            user.setAvatar("https://img1.baidu.com/it/u=2467066818,982285051&fm=26&fmt=auto&gp=0.jpg");
            userService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return  ResultUtil.error("注册信息有误");
        }
        return ResultUtil.success("注册成功");
    }

}

package com.ctgu.summer.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ctgu.summer.entity.User;
import com.ctgu.summer.service.UserService;
import com.ctgu.summer.utils.EncryptionUtils;
import com.ctgu.summer.utils.JwtUtils;
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.utils.UserUtils;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@Slf4j
@RestController
@RequestMapping("/summer/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserUtils userUtils;

    @PostMapping("/updateUser")
    @ApiOperation("更新用户信息")
    public Result updateUser(@RequestBody Map<String,Object> map) {
        // 判断是否重名
        System.out.println(map);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("nickname", map.get("nickname"));
        if (userService.getOne(qw) != null) {
            return ResultUtil.error("该昵称已存在");
        }
        // 更新资料
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("nickname", map.get("nickname"))
                .set("phone", map.get("phone"))
                .set("email", map.get("email"))
                .set("avatar", map.get("avatar"))
                .eq("id", map.get("id"));
        try {
            System.out.println("更新数据库：");
            userService.update(uw);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error("修改信息有误");
        }
        return ResultUtil.success("保存成功");
    }

    @PostMapping("/updateUserPassword")
    @ApiOperation("更新用户密码")
    public Result updateUserPassword(HttpServletRequest req, @RequestBody Map<String,String> map) {
        String authorization = req.getHeader("Authorization");
        if (StringUtils.isEmpty(req.getHeader("Authorization"))) {
            // 未登录
            return ResultUtil.error(ResultEnum.UNAUTHORIZED);
        }
        // 获取当前用户
        User user = userUtils.getCurrentUser(authorization);
        // 修改密码
        String salt = UUID.randomUUID().toString();
        user.setPassword(EncryptionUtils.getSaltMD5(map.get("newpassword"),salt));
        user.setSalt(salt);
        try {
            userService.updateById(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(ResultEnum.FAIL);
        }
        return ResultUtil.success(ResultUtil.success("修改密码成功！"));
    }



    @GetMapping("/getUserInfo")
    @ApiOperation("获取当前登录用户")
    public Result getUserInfo(HttpServletRequest req) throws Exception {
        System.out.println("捕捉到GET请求");
        String authorization = req.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return ResultUtil.error(ResultEnum.UNAUTHORIZED);
        }
        User user = userUtils.getCurrentUser(authorization);
        return ResultUtil.success("获取成功", user);
    }
}


package com.ctgu.summer.service;

import com.ctgu.summer.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
public interface UserService extends IService<User> {

    User getByNickName(String nickName);
}

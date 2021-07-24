package com.ctgu.summer.service;

import com.ctgu.summer.entity.MessageBoard;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
public interface MessageBoardService extends IService<MessageBoard> {

    List<Map<String,Object>> getAllMessage();

    List<Map<String, Object>> getMessageByDid(int id);

}

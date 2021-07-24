package com.ctgu.summer.service.impl;

import com.ctgu.summer.entity.MessageBoard;
import com.ctgu.summer.mapper.MessageBoardMapper;
import com.ctgu.summer.service.MessageBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@Service
public class MessageBoardServiceImpl extends ServiceImpl<MessageBoardMapper, MessageBoard> implements MessageBoardService {

    @Autowired
    MessageBoardMapper messageBoardMapper;
    @Override
    public List<Map<String, Object>> getAllMessage() {
       return messageBoardMapper.getListMessage();
    }

    @Override
    public List<Map<String, Object>> getMessageByDid(int id) {
        return messageBoardMapper.getMessageByDid(id);
    }

}

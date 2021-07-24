package com.ctgu.summer.mapper;

import com.ctgu.summer.entity.MessageBoard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
public interface MessageBoardMapper extends BaseMapper<MessageBoard> {

    @Select("select B.id,nickname,avatar,content,B.update_time from user,message_board B " +
            "where user.id = B.user_id and B.dis_id = -1")
    List<Map<String,Object>> getListMessage();

    @Select("select B.id,nickname,avatar,content,B.update_time from user,message_board B " +
            "where user.id = B.user_id and B.dis_id = #{id}")
    List<Map<String, Object>> getMessageByDid(int id);

}

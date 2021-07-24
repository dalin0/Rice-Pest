package com.ctgu.summer.controller;


import com.ctgu.summer.entity.MessageBoard;
import com.ctgu.summer.entity.User;
import com.ctgu.summer.service.MessageBoardService;
import com.ctgu.summer.service.UserService;
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.utils.UserUtils;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/summer/messageBoard")
public class MessageBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @Autowired
    UserUtils userUtils;

    @RequestMapping("/findAllMessage")
    @ApiOperation("查询所有用户网站留言")
    public Result findAllMessage(){
        List<Map<String, Object>> allMessage = messageBoardService.getAllMessage();
        if (allMessage == null) {
            return ResultUtil.error(ResultEnum.FAIL);
        }else{
            return ResultUtil.success("查询所有留言成功",allMessage);
        }
    }

    @GetMapping("/findMessageById/{Did}")
    @ApiOperation("根据疾病文章查询留言")
    public Result findMessageByDis(@PathVariable("Did") Integer id) {
        List<Map<String, Object>> allMessage = messageBoardService.getMessageByDid(id);
        if (allMessage == null) {
            return ResultUtil.error(ResultEnum.FAIL);
        }else{
            return ResultUtil.success("查询成功",allMessage);
        }
    }

//    @GetMapping("/findAll")
//    public Result findTest() {
//      List<MessageBoard> messageBoardList = messageBoardService.findAll();
//      return ResultUtil.success("成功",messageBoardList);
//    }

    @PostMapping("/deleteMessageById")
    @ApiOperation("删除指定用户留言")
    public Result deleteMessageById(@RequestBody Map<String,Integer> map){
        boolean is_delete = messageBoardService.removeById(map.get("Did"));
        if (is_delete){
            return ResultUtil.success("删除留言成功");
        }else{
            return ResultUtil.error("删除留言失败");
        }
    }

    @RequestMapping("/insertMessage")
    @ApiOperation("用户新增留言")
    public Result insertMessage(HttpServletRequest req,
                                @RequestBody Map<String, Object> map){
        String authorization = req.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return ResultUtil.error(ResultEnum.UNAUTHORIZED);
        }
        // 获取当前用户
        User user = userUtils.getCurrentUser(authorization);
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setUserId(user.getId());
        messageBoard.setContent(map.get("content").toString());
        messageBoard.setDisId(Integer.parseInt(map.get("disease_id").toString()));
        boolean save = messageBoardService.save(messageBoard);
        if (save) {
            return ResultUtil.success("留言成功");
        } else {
            return ResultUtil.error("保存失败");
        }
    }
}


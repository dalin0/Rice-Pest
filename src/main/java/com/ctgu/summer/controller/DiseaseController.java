package com.ctgu.summer.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ctgu.summer.dto.DiseaseDto;
import com.ctgu.summer.entity.Classify;
import com.ctgu.summer.entity.Disease;
import com.ctgu.summer.entity.MessageBoard;
import com.ctgu.summer.service.ClassifyService;
import com.ctgu.summer.service.DiseaseService;
import com.ctgu.summer.utils.ResultUtil;
import com.ctgu.summer.vo.Result;
import com.ctgu.summer.vo.ResultEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
@Slf4j
@RestController
@RequestMapping("/summer/disease")
public class  DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/findAllDisease")
    @ApiOperation("查询所有疾病")
    public Result findAllDisease(){
        List<DiseaseDto> diseaseDtoList = new ArrayList<>();
        diseaseDtoList = diseaseService.findAllDiseaseDto();
        if (diseaseDtoList == null) {
            return ResultUtil.error(ResultEnum.FAIL);
        }else{
            return ResultUtil.success("查询所有疾病成功",diseaseDtoList);
        }
    }

    @Transactional
    @PostMapping("/insertNewDisease")
    @ApiOperation("新增文章")
    public Result insertDisease(@RequestBody Map<String,Object> map) {
        Disease disease = new Disease();
        disease.setName(map.get("name").toString());
        disease.setExplanation(map.get("explanation").toString());
        disease.setDiseasePic(map.get("disease_pic").toString());
        diseaseService.save(disease);
        // 获取插入疾病百科id
        int Did = disease.getDiseaseId();
        List<String> tagNames = (List<String>) map.get("classname");
        // 将标签添加上
        for (String tag : tagNames) {
            Classify classify = new Classify();
            classify.setClassifyName(tag);
            classify.setDiseaseId(Did);
            classifyService.save(classify);
        }
        return ResultUtil.success("添加成功");
    }

    @PostMapping("/deleteDisease")
    @ApiOperation("删除文章")
    @Transactional
    public Result deleteById(@RequestBody Map<String,Object> map) {
        // 获取文章删除id
        Integer Did = (Integer) map.get("disease_id");
        // 删除classify中disease_id为Did的数据
        QueryWrapper<Classify> qw = new QueryWrapper<>();
        qw.eq("disease_id", Did);
        classifyService.remove(qw);
        // 删除Disease中id为Did的文章
        QueryWrapper<Disease> qw_dis = new QueryWrapper<>();
        qw_dis.eq("disease_id", Did);
        diseaseService.remove(qw_dis);
        return ResultUtil.success("删除成功");
    }

    @GetMapping("/getDiseaseById/{Did}")
    @ApiOperation("获取疾病百科详情")
    @Transactional
    public Result getDiseaseById(@PathVariable("Did") Integer Did) {
        // 构造返回对象
        Map<String,Object> res = new HashMap<>();
        // 查询标签
        QueryWrapper<Classify> qw = new QueryWrapper<>();
        qw.eq("disease_id", Did);
        List<Classify> classifies = classifyService.list(qw);
        res.put("classifies",classifies);
        // 查询疾病百科内容
        QueryWrapper<Disease> qw_dis = new QueryWrapper<>();
        qw_dis.eq("disease_id", Did);
        Disease disease = diseaseService.getOne(qw_dis);
        res.put("disease", disease);
        return ResultUtil.success("获取成功", res);
    }

    @PostMapping("/updateDisease")
    @ApiOperation("更新文章")
    @Transactional
    public Result updateDisease(@RequestBody Map<String, Object> map) {
        Integer Did = Integer.parseInt(map.get("disease_id").toString());
        // 更新文章
        UpdateWrapper<Disease> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("disease_id",Did)
                .set("name", map.get("name").toString())
                .set("explanation", map.get("explanation").toString())
                .set("disease_pic", map.get("dis_pic_url").toString());
        diseaseService.update(updateWrapper);
        // 更新标签
        List<String> tagname = (List<String>) map.get("tagname");
        List<String> tagid = (List<String>) map.get("classid");
        for (int i = 0; i < tagname.size(); i++) {
            UpdateWrapper<Classify> classifyWrapper = new UpdateWrapper<>();
            classifyWrapper.eq("classify_id", tagid.get(i))
                    .set("classify_name", tagname.get(i).toString());
            classifyService.update(classifyWrapper);
        }
        return ResultUtil.success("保存成功");
    }



    @RequestMapping("/findDiseaseByPageSize")
    @ApiOperation("分页查询文章信息")
    public Result findDiseaseByPageSize(Integer nowPage,Integer pageSize){
        List<DiseaseDto> diseaseDtoList = new ArrayList<>();
        diseaseDtoList = diseaseService.findDiseaseByPageSize(nowPage-1,pageSize);
        if (diseaseDtoList == null) {
            return ResultUtil.error(ResultEnum.FAIL);
        }else{
            return ResultUtil.success("查询所有疾病成功",diseaseDtoList);
        }
    }

    @RequestMapping("/findDiseaseById")
    @ApiOperation("根据ID查询疾病")
    public Result findDiseaseById(Integer diseaseId){
        DiseaseDto diseaseDto = diseaseService.findDiseaseById(diseaseId);
        if(diseaseDto == null ){
            return ResultUtil.error(ResultEnum.FAIL);
        }else{
            return ResultUtil.success("返回疾病详情成功",diseaseDto);
        }
    }

    @RequestMapping("/clickDisease")
    @ApiOperation("对文章进行点赞")
    public Result clickDisease(Integer diseaseId){
        String strDiseaseId = Integer.toString(diseaseId);
        Boolean isExistKey = redisTemplate.hasKey(strDiseaseId);
        if (isExistKey){
            Integer nowClick = (Integer) redisTemplate.opsForValue().get(strDiseaseId);
            redisTemplate.opsForValue().set(strDiseaseId,nowClick+1);
            diseaseService.clickDisease(diseaseId,nowClick+1);
            return ResultUtil.success("点赞成功",nowClick+1);
        }else {
            // 如果没有就从数据库中读取数据，存入redis中
            Disease disease = diseaseService.getById(diseaseId);
            redisTemplate.opsForValue().set(strDiseaseId,disease.getClickNum()+1);
            diseaseService.clickDisease(diseaseId,disease.getClickNum()+1);
            return ResultUtil.success("点赞成功",disease.getClickNum()+1);
        }
    }

}


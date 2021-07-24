package com.ctgu.summer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.summer.entity.Classify;
import com.ctgu.summer.entity.Disease;
import com.ctgu.summer.mapper.ClassifyMapper;
import com.ctgu.summer.mapper.DiseaseMapper;
import com.ctgu.summer.service.ClassifyService;
import com.ctgu.summer.service.DiseaseService;
import org.springframework.stereotype.Service;

@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {

}

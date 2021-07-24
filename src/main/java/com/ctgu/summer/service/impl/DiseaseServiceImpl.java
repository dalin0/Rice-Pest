package com.ctgu.summer.service.impl;

import com.ctgu.summer.dto.DiseaseDto;
import com.ctgu.summer.entity.Disease;
import com.ctgu.summer.mapper.DiseaseMapper;
import com.ctgu.summer.service.DiseaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@Service
public class DiseaseServiceImpl extends ServiceImpl<DiseaseMapper, Disease> implements DiseaseService {

    @Override
    public List<DiseaseDto> findAllDiseaseDto() {
        return this.baseMapper.findAllDiseaseDto();
    }

    @Override
    public DiseaseDto findDiseaseById(Integer diseaseId) {
        return this.baseMapper.findDiseaseById(diseaseId);
    }

    @Override
    public int clickDisease(Integer diseaseId,Integer clickNum) {
        return this.baseMapper.clickDisease(diseaseId,clickNum);
    }

    @Override
    public int findDiseaseClickById(Integer diseaseId) {
        return this.baseMapper.findDiseaseClickById(diseaseId);
    }

    @Override
    public List<DiseaseDto> findDiseaseByPageSize(Integer nowPage,Integer pageSize) {
        Integer nowNum = nowPage*pageSize;
        return this.baseMapper.findDiseaseByPageSize(nowNum,pageSize);
    }

    @Override
    public DiseaseDto findDiseaseByName(String diseaseName) {
        return this.baseMapper.findDiseaseByName(diseaseName);
    }
}

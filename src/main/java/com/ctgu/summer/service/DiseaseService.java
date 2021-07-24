package com.ctgu.summer.service;

import com.ctgu.summer.dto.DiseaseDto;
import com.ctgu.summer.entity.Disease;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
public interface DiseaseService extends IService<Disease> {

    List<DiseaseDto> findAllDiseaseDto();

    DiseaseDto findDiseaseById(Integer diseaseId);

    int clickDisease(Integer diseaseId,Integer clickNum);

    int findDiseaseClickById(Integer diseaseId);

    List<DiseaseDto> findDiseaseByPageSize(Integer nowPage,Integer pageSize);

    DiseaseDto findDiseaseByName(String diseaseName);
}

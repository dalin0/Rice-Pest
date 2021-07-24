package com.ctgu.summer.mapper;

import com.ctgu.summer.dto.DiseaseDto;
import com.ctgu.summer.entity.Disease;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
public interface DiseaseMapper extends BaseMapper<Disease> {

    List<DiseaseDto> findAllDiseaseDto();

    DiseaseDto findDiseaseById(Integer diseaseId);

    int clickDisease(@Param("diseaseId") Integer diseaseId, @Param("clickNum") Integer clickNum);

    int findDiseaseClickById(Integer diseaseId);

    List<DiseaseDto> findDiseaseByPageSize(@Param("nowNum") Integer nowNum, @Param("pageSize") Integer pageSize);

    DiseaseDto findDiseaseByName(String disease);
}

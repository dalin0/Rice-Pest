package com.ctgu.summer.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ctgu.summer.entity.Classify;
import com.ctgu.summer.entity.DisImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDto {

    @ApiModelProperty(value = "疾病ID")
    private Integer id;

    @ApiModelProperty(value = "疾病名称")
    private String diseaseName;

    @ApiModelProperty(value = "疾病描述")
    private String diseaseExplanation;

    @ApiModelProperty(value = "点赞数量")
    private Integer clickNum;

    @ApiModelProperty(value = "封面图片链接")
    private String picUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "分类名称列表")
    private List<Classify> classifyList;

}

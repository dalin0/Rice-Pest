package com.ctgu.summer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Disease对象", description="")
public class Disease implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "疾病ID")
    @TableId(value = "disease_id", type = IdType.AUTO)
    private Integer diseaseId;

    @ApiModelProperty(value = "疾病名称")
    private String name;

    @ApiModelProperty(value = "疾病说明")
    private String explanation;

    @ApiModelProperty(value = "点赞次数")
    private Integer clickNum;

    @ApiModelProperty(value = "疾病图片")
    private String diseasePic;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

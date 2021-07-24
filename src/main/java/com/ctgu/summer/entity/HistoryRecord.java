package com.ctgu.summer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@ApiModel(value="HistoryRecord对象", description="")
public class HistoryRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "历史记录ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "疾病名称")
    private String diseaseName;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "用户id 外键")
    private Integer userId;

    @ApiModelProperty(value = "准确率")
    private Float accuracy;

    @ApiModelProperty(value = "用户上传图片")
    private String pic;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}

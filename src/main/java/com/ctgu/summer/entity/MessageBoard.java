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
@ApiModel(value="MessageBoard对象", description="")
public class MessageBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "留言ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "回复")
    private String reply;

    @ApiModelProperty(value = "主题")
    private String theme;

    @ApiModelProperty(value = "文章下留言id，-1为网站留言")
    private Integer disId;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}

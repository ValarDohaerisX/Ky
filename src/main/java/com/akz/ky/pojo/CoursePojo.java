package com.akz.ky.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author AKZ
 * @despriction 课程信息表
 * @date 2019-09-05
 *
 */

@Data
public class CoursePojo {

    @ApiModelProperty("主键")
    int id;
    @ApiModelProperty("一级学科id")
    int fid;
    @ApiModelProperty("二级学科id")
    int sid;
    @ApiModelProperty("专业名称")
    String name;
    @ApiModelProperty("专业代码")
    int code;
    @ApiModelProperty("专业类型")
    String type;
    

}

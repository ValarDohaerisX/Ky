package com.akz.ky.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/10 11:51
 * @Description
 */
@Data
public class FirstCoursePojo {

    @ApiModelProperty("主键")
    private int firstCourseNo;

    private String firstCourseCode;

    private String firstCourseName;

    private List<SecondCoursePojo> secondCoursePojos;
}

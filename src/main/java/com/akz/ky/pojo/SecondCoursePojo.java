package com.akz.ky.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 9:44
 * @Description
 */
@Data
public class SecondCoursePojo {

    //二级学科主键
    int secondCourseNo;
    //二级学科代码
    String secondCourseCode;
    //二级学科名称N
    String secondCourseName;
    //专业类型
    String majorType;
    /*一级学科信息*/
    int firstCourseNo;
    String firstCourseCode;
    FirstCoursePojo firstCoursePojo;

    List<CoursePojo> coursePojos;
}

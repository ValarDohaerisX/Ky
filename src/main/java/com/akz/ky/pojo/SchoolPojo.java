package com.akz.ky.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author AKZ
 * @despriction 目标院校信息表
 * @date 2019-09-05
 *
 */

@Data
public class SchoolPojo {
    //主键
    int schoolNo;

    String schoolName;

    String schoolType;

    //学校代码
    String schoolCode;

    //学校简介
    String schoolInfo;

    String schoolLevel;

    String address;

    List<MajorPojo> majors;
}

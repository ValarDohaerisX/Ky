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
    //学校名称
    String schoolName;
    //学校代码
    String schoolCode;
    //学校类型
    String schoolType;
    //学校水平
    String schoolLevel;
    //所在省份
    String address;
    //学校简介
    String schoolInfo;
    //学校邮箱
    String schoolMail;
    //学校联系方式
    String schoolMobile;
    //学校详细地址
    String schoolAddress;
    //学校官网
    String schoolOnlineNet;
    //学校所在研究生官网
    String schoolKyStudentOnlineNet;

    List<MajorPojo> majors;

}


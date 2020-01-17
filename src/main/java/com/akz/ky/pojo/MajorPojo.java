package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author AKZ
 * @despriction 目标院校招收专业信息表
 * @date 2019-09-05
 *
 */
@Data
public class MajorPojo {
//    id,schoolId,majorCode,majorName,majorLowScore,majorHighScore,
// examNum,receivedNum,scoreNumber,student,currYear
    //主键
    int majorNo;

    int firstCourseNo;

    int secondCourseNo;

    //学校代码
    String schoolNo;

    //专业代码
    String majorCode;

    //专业名称
    String majorName;

    //专业类型
    String majorType;

    //联系人名称
    String techName;


    //联系人电话
    String techMobile;

    //最低分数线
    float majorLowScoreLine;

    //最低分数
    float majorLowScore;

    //最高分数
    float majorHighScore;

    //报考人数
    int examNum;

    //招录人数
    int receivedNum;

    int scoreNumber;
    //近5年
    String currYear;

    SchoolPojo schoolPojo;

    FirstCoursePojo firstCoursePojo;

    SecondCoursePojo secondCoursePojo;

}

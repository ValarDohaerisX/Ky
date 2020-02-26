package com.akz.ky.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/2/17 21:56
 * @Description
 */
@Data
public class SchoolDetailRequestPojo {
    //院校号
    String schoolNo;
    //院校名称
    String schoolName;
    SchoolPojo schoolPojo;
    //目标院校基本信息
    //学院信息
    List<DepartmentPojo> departmentForm;
    //综合信息
    List<TitleForm> titleForm;
    List<GetStudentForm> getStudentForm;
    List<DispensingForm> dispensingForm;
    DescribeForm describeForm;
    List<ScholarshipForm> scholarshipForm;
//    List<List<SchoolMainInfoPojo>> schoolMainInfoPojos;
}

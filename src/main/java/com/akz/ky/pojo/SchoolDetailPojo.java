package com.akz.ky.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/6 11:21
 * @Description 目标院校明细页面请求参数实体类
 */
@Data
public class SchoolDetailPojo {

    //院校号
    String schoolNo;
    //院校名称
    String schoolName;
    SchoolPojo schoolPojo;
    //目标院校基本信息
    //学院信息
    List<DepartmentPojo> departmentPojos;
    //综合信息
    List<List<SchoolMainInfoPojo>> schoolMainInfoPojos;

}

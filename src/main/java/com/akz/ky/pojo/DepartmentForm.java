package com.akz.ky.pojo;

import lombok.Data;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/2/17 21:47
 * @Description 院校明细表保存功能所需参数
 */
@Data
public class DepartmentForm {

    int departmentId;

    String  departmentNo;

    String departmentName;

    String schoolNo;

    String schoolName;

    int majorNo;

    String majorName;

    String majorCode;

    String majorType;

    MajorPojo majorPojo;
}

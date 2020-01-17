package com.akz.ky.pojo;

import lombok.Data;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/6 11:25
 * @Description 招生院系信息
 */
@Data
public class DepartmentPojo {

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

package com.akz.ky.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/24 21:29
 * @Description 考研专业搜索页面初始化请求参数
 */
@Data
public class MajorIndexRequestPojo {

    private String majorCode;

    private String majorName;

    private String majorType;

    private String schoolCode;

    private String schoolName;

    private String firstCourseCode;

    private String firstCourseName;

    private String secondCourseCode;

    private String secondCourseName;

    //重写equals方法,若majorCode相同，则两个对象相同
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MajorIndexRequestPojo)) {
            return false;
        }
        MajorIndexRequestPojo stu = (MajorIndexRequestPojo) obj;
        return  this.majorCode.equals(stu.majorCode);
    }
    //重写hashCode方法，若majorCode相同，则两个对象相同
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (majorCode == null ? 0 : majorCode.hashCode());
        return result;
    }
//    private MajorPojo majorPojo;
//
//    private List<SchoolPojo> schoolPojos;

}

package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/10 11:55
 * @Description
 */

public interface FirstCourseService {
    Result add(FirstCoursePojo firstCoursePojo);

    Result delete(int id);

    Result<List<FirstCoursePojo>> getByName(String name);

    Result<FirstCoursePojo> getByCode(String code);

    Result<List<FirstCoursePojo>> listByAll();

    Result updateFirstCourse(FirstCoursePojo firstCoursePojo);

}

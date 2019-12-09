package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;
import com.akz.ky.pojo.SecondCoursePojo;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 10:09
 * @Description
 */
public interface SecondCourseService {

    Result<SecondCoursePojo> getById(int id);

    Result<List<SecondCoursePojo>> getByLikeCode(String code);

    Result<List<SecondCoursePojo>> getByName(String name);

    Result<List<SecondCoursePojo>> getAll();

    Result<List<SecondCoursePojo>> getByFirstCourseCode(String firstCode);

    Result delete(int id);

    Result<SecondCoursePojo> add(SecondCoursePojo secondCoursePojo);

    Result update(SecondCoursePojo secondCoursePojo);

    void fill(List<SecondCoursePojo> secondCoursePojos, FirstCoursePojo firstCoursePojo);

    void fill(List<FirstCoursePojo> firstCoursePojos);
}

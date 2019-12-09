package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.CoursePojo;

import java.util.List;

public interface CourseService {

    boolean add(CoursePojo coursePojo);

    boolean delete(int id);

    boolean update(CoursePojo coursePojo);

    Result<CoursePojo> getById(int id);

    Result<List<CoursePojo>> getByName(String name);

    Result<CoursePojo> getByCode(String code);

    Result<List<CoursePojo>> listByType(String type);

    Result<List<CoursePojo>> listByAll();

}

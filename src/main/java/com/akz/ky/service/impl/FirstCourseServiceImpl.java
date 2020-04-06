package com.akz.ky.service.impl;

import com.akz.ky.mapper.FirstCourseMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;
import com.akz.ky.service.FirstCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/10 11:55
 * @Description 学科门类服务类
 */
@Service
@CacheConfig(cacheNames = "firstCourses")
public class FirstCourseServiceImpl implements FirstCourseService{

    @Autowired(required = false)
    FirstCourseMapper firstCourseMapper;
    @Override
    @CacheEvict(allEntries = true)
    public Result add(FirstCoursePojo firstCoursePojo) {
        System.out.println("进入到添加学科信息后台");
        List<FirstCoursePojo> firstCoursePojos = firstCourseMapper.listByAll();
        String code = firstCoursePojo.getFirstCourseCode();
        String name = firstCoursePojo.getFirstCourseName();
        for (FirstCoursePojo temp : firstCoursePojos) {
            if (temp.getFirstCourseName().equals(firstCoursePojo.getFirstCourseName()) || temp.getFirstCourseCode().equals(firstCoursePojo.getFirstCourseCode()))
                return Result.failure(ApiReturnCode.C_Fail_Insert_Repeat, "代码:[" + code + "] 或 学科:[" + name + "] 信息已存在,添加失败！");
        }

        boolean flag = firstCourseMapper.add(firstCoursePojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Insert, "学科门类信息添加失败,请稍后重试~");
        FirstCoursePojo firstCourseMapperById = firstCourseMapper.getByFirstCourseNo(firstCoursePojo.getFirstCourseNo());
        System.out.println("firstCourseMapperById:"+firstCourseMapperById);
        return Result.success(firstCourseMapperById);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result delete(int id) {
        boolean flag = firstCourseMapper.delete(id);
        if (flag)
            return Result.success(flag);
        else
            return Result.failure(ApiReturnCode.C_Fail_Delete,"删除学科信息失败，请稍后再试~");
    }

    @Override
    @Cacheable(key="'schoolPojos-one-byName-'+ #p0")
    public Result<List<FirstCoursePojo>> getByName(String name) {
        return null;
    }

    @Override
    @Cacheable(key="'schoolPojos-one-byCode-'+ #p0")
    public Result<FirstCoursePojo> getByCode(String code) {
        return null;
    }

    @Override
    @Cacheable(key="'firstCourses-all'")
    public Result<List<FirstCoursePojo>> listByAll() {
        List<FirstCoursePojo> firstCoursePojos = firstCourseMapper.listByAll();
        if (firstCoursePojos != null){
            return Result.success(firstCoursePojos);
        }else
            return Result.failure(ApiReturnCode.C_Fail_Get,"学科信息获取失败！");
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result updateFirstCourse(FirstCoursePojo firstCoursePojo) {
        System.out.println("后台firstCoursePojo:"+firstCoursePojo);
        boolean flag = firstCourseMapper.updateFirstCourse(firstCoursePojo);
        if (!flag)
            Result.failure(ApiReturnCode.C_Fail_Update,"学科信息更新失败");
        FirstCoursePojo firstCoursePojo1 = firstCourseMapper.getByFirstCourseNo(firstCoursePojo.getFirstCourseNo());
        return Result.success(firstCoursePojo1);
    }
}

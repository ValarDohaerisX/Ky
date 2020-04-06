package com.akz.ky.service.impl;

import com.akz.ky.mapper.CourseMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.CoursePojo;
import com.akz.ky.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "courses")
public class CourseServiceImpl implements CourseService {

    @Autowired(required = false)
    CourseMapper courseMapper;
    @Override
    @CacheEvict(allEntries = true)
    public boolean add(CoursePojo coursePojo) {
        return courseMapper.add(coursePojo);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean delete(int id) {
        return courseMapper.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public boolean deleteByIds(List<String> ids) {
        boolean f = true;
        for (String str : ids) {
            int id = Integer.parseInt(str);
            boolean b = delete(id);
            if (!b){
                f = false;
                break;
            }
        }
        return f;
    }
    @Override
    @CacheEvict(allEntries = true)
    public boolean update(CoursePojo coursePojo) {
        return courseMapper.updateCourse(coursePojo);
    }

    @Override
    @Cacheable(key="'courses-one-byId-'+ #p0")
    public Result<CoursePojo> getById(int id) {
        CoursePojo coursePojo = courseMapper.getById(id);
        if (coursePojo == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"无此课程信息");
        }
        return Result.success(coursePojo);
    }

    @Override
    @Cacheable(key="'courses-one-byName-'+ #p0")
    public Result<List<CoursePojo>> getByName(String name) {
        List<CoursePojo> coursePojos = courseMapper.getByName(name);
        if (coursePojos == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询: "+name+"课程信息为空,请重新输入");
        }
        if (coursePojos.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询: "+name+"课程信息为空,请重新输入");
        return Result.success(coursePojos);
    }

    @Override
    @Cacheable(key="'courses-one-byCode-'+ #p0")
    public Result<CoursePojo> getByCode(String code) {
        CoursePojo coursePojo = courseMapper.getByCode(code);
        if (coursePojo == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"代码:"+code+"专业信息查询为空,请重新输入");
        }
        return Result.success(coursePojo);
    }

    @Override
    @Cacheable(key="'courses-one-byType-'+ #p0")
    public Result<List<CoursePojo>> listByType(String type) {
        List<CoursePojo> coursePojos = courseMapper.listByType(type);
        if (coursePojos == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"无 "+type+" 类课程信息,请重新输入");
        }
        return Result.success(coursePojos);
    }

    @Override
    @Cacheable(key="'courses-all")
    public Result<List<CoursePojo>> listByAll() {
        List<CoursePojo> coursePojos = courseMapper.listByAll();
        if (coursePojos == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据查询失败");
        }
        return Result.success(coursePojos);
    }

}

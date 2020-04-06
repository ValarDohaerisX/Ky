package com.akz.ky.service.impl;

import com.akz.ky.mapper.FirstCourseMapper;
import com.akz.ky.mapper.SecondCourseMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;
import com.akz.ky.pojo.SecondCoursePojo;
import com.akz.ky.service.SecondCourseService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 10:12
 * @Description
 */
@Service
@CacheConfig(cacheNames = "secondCourses")
public class SecondCourseServiceImpl implements SecondCourseService {


    @Autowired(required = false)
    SecondCourseMapper secondCourseMapper;

    @Autowired(required = false)
    FirstCourseMapper firstCourseMapper;
    @Override
    @Cacheable(key="'secondCourses-one-'+ #p0")
    public Result<SecondCoursePojo> getById(int id) {
        SecondCoursePojo secondCoursePojo = secondCourseMapper.getById(id);
        if (secondCoursePojo == null){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        fill(secondCoursePojo);
        return Result.success(secondCoursePojo);
    }
    public void fill(SecondCoursePojo secondCoursePojo){
//        int cid = secondCoursePojo.getFirstCId();
        String firstCode = secondCoursePojo.getFirstCourseCode();
        FirstCoursePojo firstCoursePojo = firstCourseMapper.getByCode(firstCode);
        secondCoursePojo.setFirstCourseCode(firstCoursePojo.getFirstCourseCode());
        secondCoursePojo.setFirstCoursePojo(firstCoursePojo);
    }
    @Override
    @Cacheable(key="'secondCourses-byCodes'+ #p0")
    public Result<List<SecondCoursePojo>> getByLikeCode(String code) {
        List<SecondCoursePojo> byCode = secondCourseMapper.getByLikeCode(code);
        if (byCode == null || byCode.size()==0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        System.out.println("11");
        return Result.success(byCode);
    }

    @Override
    @Cacheable(key="'secondCourses-byNames'+ #p0")
    public Result<List<SecondCoursePojo>> getByName(String name) {
        List<SecondCoursePojo> byCode = secondCourseMapper.getByName(name);
        if (byCode == null || byCode.size()==0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(byCode);
    }

    @Override
    @Cacheable(key="'secondCourses-all'")
    public Result<List<SecondCoursePojo>> getAll() {
        List<SecondCoursePojo> all = secondCourseMapper.getAll();
        if (all == null){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        fill(firstCourseMapper.listByAll());
        return Result.success(all);
    }

    @Override
    @Cacheable(key="'secondCourses-byFirstCourseCodes-'+#p0")
    public Result<List<SecondCoursePojo>> getByFirstCourseCode(String firstCode) {
        System.out.println("firstCode:"+firstCode);
        List<SecondCoursePojo> byCode = secondCourseMapper.getByFirstCourseCode(firstCode);
        if (byCode == null || byCode.size()==0){
            return Result.failure(ApiReturnCode.C_Fail_Get,"该分类下未查询到相关专业信息");
        }
        FirstCoursePojo firstCoursePojo = firstCourseMapper.getByCode(firstCode);
        fill(byCode,firstCoursePojo);
        return Result.success(byCode);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result delete(int id) {
        boolean flag = secondCourseMapper.delete(id);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Delete,"删除失败");
        return Result.success(flag);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result<SecondCoursePojo> add(SecondCoursePojo secondCoursePojo) {
        String firstCode = secondCoursePojo.getFirstCourseCode();
        FirstCoursePojo byCode = firstCourseMapper.getByCode(firstCode);
        secondCoursePojo.setFirstCourseNo(byCode.getFirstCourseNo());
        secondCoursePojo.setFirstCoursePojo(byCode);
        boolean flag = secondCourseMapper.add(secondCoursePojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Insert,"添加失败");
        System.out.println("S-ID:"+secondCoursePojo.getSecondCourseNo());
        fill(secondCoursePojo);
        return Result.success(secondCoursePojo);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result update(SecondCoursePojo secondCoursePojo) {
        Result<SecondCoursePojo> byId = getById(secondCoursePojo.getSecondCourseNo());
        if (byId.getReturnCode().getCode() == 1004)
            return byId;
        boolean flag = secondCourseMapper.update(secondCoursePojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Update);
        return Result.success(flag);
    }

    @Override
    public void fill(List<SecondCoursePojo> secondCoursePojos, FirstCoursePojo firstCoursePojo) {
//        List<SecondCoursePojo> secondCoursePojos = secondCourseMapper.getByFirstCourseId(firstCoursePojo.getId());
        for(SecondCoursePojo sc : secondCoursePojos){
            sc.setFirstCourseNo(firstCoursePojo.getFirstCourseNo());
            sc.setFirstCourseCode(firstCoursePojo.getFirstCourseCode());
            sc.setFirstCoursePojo(firstCoursePojo);
        }
    }

    @Override
    public void fill(List<FirstCoursePojo> firstCoursePojos) {
        List<SecondCoursePojo> body = getAll().getBody();
        for(SecondCoursePojo sc : body){
            FirstCoursePojo byId = firstCourseMapper.getByFirstCourseNo(sc.getFirstCourseNo());
            sc.setFirstCoursePojo(byId);
        }
    }
}

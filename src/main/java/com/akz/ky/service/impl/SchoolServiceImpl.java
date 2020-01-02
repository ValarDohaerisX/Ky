package com.akz.ky.service.impl;

import com.akz.ky.mapper.SchoolMainInfoMapper;
import com.akz.ky.mapper.SchoolMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.SchoolPojo;
import com.akz.ky.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 14:23
 * @Description
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired(required = false)
    SchoolMapper schoolMapper;
    @Autowired(required = false)
    SchoolMainInfoMapper schoolMainInfoMapper;


    @Override
    public Result add(SchoolPojo schoolPojo) {
        if (schoolPojo == null)
            return Result.failure(ApiReturnCode.C_Fail);
        boolean flag = schoolMapper.add(schoolPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Insert);
        return Result.success(flag);
    }

    @Override
    public Result update(SchoolPojo schoolPojo) {
        if (schoolPojo == null)
            return Result.failure(ApiReturnCode.C_Fail);
        boolean flag = schoolMapper.update(schoolPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Update);
        return Result.success(flag);
    }

    @Override
    public Result<SchoolPojo> getBySchoolNo(String schoolNo) {
        SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo(schoolNo);
        if (bySchoolNo == null)
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据不存在");
        return Result.success(bySchoolNo);
    }

    @Override
    public Result<List<SchoolPojo>> getAll() {
        List<SchoolPojo> all = schoolMapper.getAll();
        if (all == null || all.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据为空");
        }
        return Result.success(all);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolNameLike(String schoolName) {
        List<SchoolPojo> bySchoolNameLike = schoolMapper.getBySchoolNameLike(schoolName);
        if (bySchoolNameLike == null || bySchoolNameLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolNameLike);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolTypeLike(String schoolType) {
        List<SchoolPojo> bySchoolTypeLike = schoolMapper.getBySchoolTypeLike(schoolType);
        if (bySchoolTypeLike == null || bySchoolTypeLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolTypeLike);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolLevelLike(String schoolLevel) {
        List<SchoolPojo> bySchoolLevelLike = schoolMapper.getBySchoolLevelLike(schoolLevel);
        if (bySchoolLevelLike == null || bySchoolLevelLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolLevelLike);
    }

    @Override
    public Result<List<SchoolPojo>> getByAddressLike(String address) {
        List<SchoolPojo> byAddressLike = schoolMapper.getByAddressLike(address);
        checkIfEmpty(byAddressLike);
        return Result.success(byAddressLike);
    }

    @Override
    public Result delete(String schoolNo) {
        boolean flag = schoolMapper.delete(schoolNo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Delete);
        return Result.success(flag);
    }
    public Result checkIfEmpty(List<SchoolPojo> schoolPojos){
        if (schoolPojos == null || schoolPojos.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ApiReturnCode.SUCCESS);
    }
}

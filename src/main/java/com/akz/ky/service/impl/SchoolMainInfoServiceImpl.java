package com.akz.ky.service.impl;

import com.akz.ky.mapper.SchoolMainInfoMapper;
import com.akz.ky.mapper.SchoolMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.SchoolMainInfoPojo;
import com.akz.ky.service.SchoolMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/24 18:44
 * @Description
 */
@Service
public class SchoolMainInfoServiceImpl implements SchoolMainInfoService {

    @Autowired(required = false)
    SchoolMainInfoMapper schoolMainInfoMapper;
    @Autowired(required = false)
    SchoolMapper schoolMapper;

    @Override
    public Result insert(SchoolMainInfoPojo schoolMainInfoPojo) {
        if (schoolMainInfoPojo == null)
            return Result.failure(ApiReturnCode.C_Fail_Insert,"存入数据为空");
        boolean flag = schoolMainInfoMapper.add(schoolMainInfoPojo);
        return Result.success(flag);
    }

    @Override
    public Result update(SchoolMainInfoPojo schoolMainInfoPojo) {
        if (schoolMainInfoPojo == null)
            return Result.failure(ApiReturnCode.C_Fail_Insert,"更新数据为空");
        boolean flag = schoolMainInfoMapper.update(schoolMainInfoPojo);
        return Result.success(flag);
    }

    @Override
    public Result<SchoolMainInfoPojo> getByInfoNoAndSchoolNo(String infoNo, String schoolNo) {
        SchoolMainInfoPojo schoolMainInfoPojo  = schoolMainInfoMapper.getByInfoNoAndSchoolNo(infoNo, schoolNo);
        if (schoolMainInfoPojo == null)
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据为空");
        return Result.success(schoolMainInfoPojo);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getBySchoolNo(String schoolNo) {
        List<SchoolMainInfoPojo> schoolMainInfoPojos = schoolMainInfoMapper.getBySchoolNo(schoolNo);
        if (schoolMainInfoPojos == null || schoolMainInfoPojos.size()==0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(schoolMainInfoPojos);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByInfoType(String infoType, String schoolNo) {
        List<SchoolMainInfoPojo> byInfoType = schoolMainInfoMapper.getByInfoType(infoType, schoolNo);
        if (byInfoType == null || byInfoType.size()==0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(byInfoType);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByDescribe(String schoolNo) {
        List<SchoolMainInfoPojo> byDescribe = schoolMainInfoMapper.getByDescribe(schoolNo);
        if(byDescribe == null || byDescribe.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get);
        return Result.success(byDescribe);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByTitle(String schoolNo) {
        List<SchoolMainInfoPojo> byTitle = schoolMainInfoMapper.getByTitle(schoolNo);
        if(byTitle == null || byTitle.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get);
        return Result.success(byTitle);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByGetStudent(String schoolNo) {
        List<SchoolMainInfoPojo> byGetStudent = schoolMainInfoMapper.getByGetStudent(schoolNo);
        if(byGetStudent == null || byGetStudent.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get);
        return Result.success(byGetStudent);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByDispensing(String schoolNo) {
        List<SchoolMainInfoPojo> byDispensing = schoolMainInfoMapper.getByDispensing(schoolNo);
        if(byDispensing == null || byDispensing.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get);
        return Result.success(byDispensing);
    }

    @Override
    public Result<List<SchoolMainInfoPojo>> getByScholalShip(String schoolNo) {
        List<SchoolMainInfoPojo> byScholalShip = schoolMainInfoMapper.getByScholalShip(schoolNo);
        if(byScholalShip == null || byScholalShip.size() == 0)
            return Result.failure(ApiReturnCode.C_Fail_Get);
        return Result.success(byScholalShip);
    }

    @Override
    public Result delete(String infoNo) {
        boolean flag = schoolMainInfoMapper.delete(infoNo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Delete);
        return Result.success(flag);
    }

}

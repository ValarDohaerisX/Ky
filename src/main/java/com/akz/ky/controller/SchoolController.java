package com.akz.ky.controller;

import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.LdcodePojo;
import com.akz.ky.pojo.SchoolDetailPojo;
import com.akz.ky.pojo.SchoolPojo;
import com.akz.ky.service.LdcodeService;
import com.akz.ky.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 17:29
 * @Description
 */

@RestController
@RequestMapping("school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    @Autowired(required = false)
    LdcodeService ldcodeService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Result insert(@RequestBody SchoolPojo schoolPojo){
        return schoolService.add(schoolPojo);
    }

    /**
     * 查询目标院校明细信息
     * @param schoolNo
     * @return SchoolDetailPojo
     */
    @RequestMapping(value = "getSchoolDetailInfo",method = RequestMethod.POST)
    public Result<SchoolDetailPojo> getSchoolDetailInfo(@RequestParam String schoolNo){
        return schoolService.getSchoolDetailInfo(schoolNo);
    }

    /**
     * 添加/修改目标院校明细信息
     * @param schoolDetailPojo
     * @return SchoolDetailPojo
     */
    @RequestMapping(value = "setSchoolDetailInfo",method = RequestMethod.POST)
    public Result<SchoolDetailPojo> setSchoolDetailInfo(@RequestBody SchoolDetailPojo schoolDetailPojo){
        return schoolService.setSchoolDetailInfo(schoolDetailPojo);
    }
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Result update(@RequestBody SchoolPojo schoolPojo){
        return schoolService.update(schoolPojo);
    }

    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public Result getAll(){
        return schoolService.getAll();
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Result delete(@RequestParam String schoolNo){
        return schoolService.delete(schoolNo);
    }

    @RequestMapping(value = "getCodeType",method = RequestMethod.POST)
    public Result getCodeType(@RequestParam String codeType){
        List<LdcodePojo> ldCodeByCodeType = ldcodeService.getLdCodeByCodeType(codeType);
        if (ldCodeByCodeType == null || ldCodeByCodeType.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ldCodeByCodeType);
    }
    @RequestMapping(value = "getSchoolType",method = RequestMethod.GET)
    public Result getSchoolType(){
        List<LdcodePojo> ldCodeByCodeType = ldcodeService.getLdCodeBySchoolType();
        if (ldCodeByCodeType == null || ldCodeByCodeType.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ldCodeByCodeType);
    }

    @RequestMapping(value = "getSchoolLevel",method = RequestMethod.GET)
    public Result getSchoolLevel(){
        List<LdcodePojo> ldCodeBySchoolLevel = ldcodeService.getLdCodeBySchoolLevel();
        if (ldCodeBySchoolLevel == null || ldCodeBySchoolLevel.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ldCodeBySchoolLevel);
    }

    @RequestMapping(value = "getSchoolAddress",method = RequestMethod.GET)
    public Result getSchoolAddress(){
        List<LdcodePojo> ldCodeByAddress = ldcodeService.getLdCodeByAddress();
        if (ldCodeByAddress == null || ldCodeByAddress.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ldCodeByAddress);
    }

}

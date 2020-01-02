package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.SchoolMainInfoPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/24 18:38
 * @Description 目标院校主要信息服务类
 */
public interface SchoolMainInfoService {

    public Result insert(SchoolMainInfoPojo schoolMainInfoPojo);

    public Result update(SchoolMainInfoPojo schoolMainInfoPojo);

    public Result<SchoolMainInfoPojo> getByInfoNoAndSchoolNo(String infoNo,String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getBySchoolNo(String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByInfoType(String infoType,String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByDescribe(String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByTitle(String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByGetStudent(String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByDispensing(String schoolNo);

    public Result<List<SchoolMainInfoPojo>> getByScholalShip(String schoolNo);

    public Result delete(String infoNo);
}

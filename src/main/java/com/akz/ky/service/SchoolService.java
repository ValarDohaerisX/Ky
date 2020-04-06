package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.SchoolDetailPojo;
import com.akz.ky.pojo.SchoolDetailRequestPojo;
import com.akz.ky.pojo.SchoolMainInfoPojo;
import com.akz.ky.pojo.SchoolPojo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 14:18
 * @Description
 */
public interface SchoolService {

    public static final String getStudent = "getStudent";

    public static final String title = "title";

    public static final String dispensing = "dispensing";

    public static final String describe = "describe";

    public static final String scholarship = "scholarship";

    public Result add(SchoolPojo schoolPojo);

    public Result update(SchoolPojo schoolPojo);

    public Result<SchoolPojo> getBySchoolNo (String schoolNo);

    public Result<List<SchoolPojo>> getAll();

    public Result<List<SchoolPojo>> getBySchoolNameLike(String schoolName);

    public Result<List<SchoolPojo>> getBySchoolTypeLike(String schoolType);

    public Result<List<SchoolPojo>> getBySchoolLevelLike(String schoolLevel);

    public Result<List<SchoolPojo>> getByAddressLike(String address);

    public Result delete(String schoolNo);

    public Result<SchoolDetailPojo> getSchoolDetailInfo(String schoolNo);

    public Result<SchoolDetailPojo> setSchoolDetailInfo(SchoolDetailRequestPojo schoolDetailRequestPojo);

    public LinkedHashMap<String,List<SchoolMainInfoPojo>> getSchoolMainInfoMaps(List<SchoolMainInfoPojo> schoolMainInfoPojos);

    public Result<SchoolPojo> getBySchoolCode(String schoolCode);
}

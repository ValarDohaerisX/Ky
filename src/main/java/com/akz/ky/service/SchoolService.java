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

    Result add(SchoolPojo schoolPojo);

    Result update(SchoolPojo schoolPojo);

    Result<SchoolPojo> getBySchoolNo (String schoolNo);

    Result<List<SchoolPojo>> getAll();

    Result<List<SchoolPojo>> getBySchoolNameLike(String schoolName);

    Result<List<SchoolPojo>> getBySchoolTypeLike(String schoolType);

    Result<List<SchoolPojo>> getBySchoolLevelLike(String schoolLevel);

    Result<List<SchoolPojo>> getByAddressLike(String address);

    Result delete(String schoolNo);

    Result<SchoolDetailPojo> getSchoolDetailInfo(String schoolNo);

    Result<SchoolDetailPojo> setSchoolDetailInfo(SchoolDetailRequestPojo schoolDetailRequestPojo);

    LinkedHashMap<String,List<SchoolMainInfoPojo>> getSchoolMainInfoMaps(List<SchoolMainInfoPojo> schoolMainInfoPojos);

}

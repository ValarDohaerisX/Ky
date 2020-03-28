package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.MajorIndexRequestPojo;
import com.akz.ky.pojo.MajorPojo;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/6 15:50
 * @Description
 */

public interface MajorService {
    public Result<MajorPojo> add(MajorPojo majorPojo);

    Result<List<MajorPojo>> getMajor(int firstCourseNo, int secondCourseNo);

    Result<List<MajorPojo>> getMajorBySchoolNo(int schoolNo);

    Result updateMajor(MajorPojo majorPojo);

    Result deleteMajor(int majorNo);

    Result<MajorPojo> getByMajorCode(String majorCode);

    Result<MajorPojo> getByMajorName(String majorName);

    Result<List<MajorPojo>> getAll();

    List<MajorIndexRequestPojo> majorIndexRequest();
}

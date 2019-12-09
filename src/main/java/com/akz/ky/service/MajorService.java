package com.akz.ky.service;

import com.akz.ky.message.Result;
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

    Result updateMajor(MajorPojo majorPojo);

    Result deleteMajor(int majorNo);
}

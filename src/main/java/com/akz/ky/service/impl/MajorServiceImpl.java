package com.akz.ky.service.impl;

import cn.hutool.core.date.DateUtil;
import com.akz.ky.mapper.FirstCourseMapper;
import com.akz.ky.mapper.MajorMapper;
import com.akz.ky.mapper.SecondCourseMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;
import com.akz.ky.pojo.MajorIndexRequestPojo;
import com.akz.ky.pojo.MajorPojo;
import com.akz.ky.pojo.SecondCoursePojo;
import com.akz.ky.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/6 15:52
 * @Description
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired(required = false)
    MajorMapper majorMapper;
    @Autowired(required = false)
    FirstCourseMapper firstCourseMapper;
    @Autowired(required = false)
    SecondCourseMapper secondCourseMapper;
    @Override
    public Result<MajorPojo> add(MajorPojo majorPojo) {
        MajorPojo exist = majorMapper.isExist(majorPojo.getMajorCode(), majorPojo.getMajorName());
        System.out.println("1");
        if (exist != null){
            return Result.failure(ApiReturnCode.C_Fail_Insert,"专业信息已存在，请勿重复添加");
        }
//        List<MajorPojo> majorPojos = majorMapper.getByMajorCode(majorPojo.getMajorCode());
//        if (majorPojos.size() > 1){
//            for (MajorPojo majorPojo1:majorPojos) {
//                if (majorPojo.getMajorName().equals(majorPojo1.getMajorName())){
//                    return Result.failure(ApiReturnCode.C_Fail_Insert,"专业信息已存在，请勿重复添加");
//                }
//            }
//        }
        boolean flag = majorMapper.add(majorPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Insert);
        fill(majorPojo);
        return Result.success(majorPojo);
    }

    @Override
    public Result<List<MajorPojo>> getMajor(int firstCourseNo, int secondCourseNo) {
        List<MajorPojo> majorPojos = majorMapper.listByFS(firstCourseNo, secondCourseNo);
        if (majorPojos==null||majorPojos.size()==0)
            return Result.failure(ApiReturnCode.C_Fail_Get,"专业信息查询失败");
        fill(majorPojos);
        return Result.success(majorPojos);
    }

    @Override
    public Result<List<MajorPojo>> getMajorBySchoolNo(int schoolNo) {
        List<MajorPojo> bySchoolNo = majorMapper.getBySchoolNo(schoolNo);
        return Result.success(bySchoolNo);
    }

    @Override
    public Result updateMajor(MajorPojo majorPojo) {
        if (majorPojo == null){
            return Result.failure(ApiReturnCode.C_Fail_Update,"获取不到需更新的专业信息，请检查异常.");
        }
        boolean flag = majorMapper.updateMajor(majorPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Update,"专业信息更新失败");
        return Result.success(flag);
    }

    @Override
    public Result deleteMajor(int majorNo) {
        if (0 == majorNo){
            return Result.failure(ApiReturnCode.C_Fail_Delete,"删除失败，接收参数值为0");
        }
        boolean flag = majorMapper.deleteMajor(majorNo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Delete);
        return Result.success(flag);
    }

    @Override
    public Result<MajorPojo> getByMajorCode(String majorCode) {
        MajorPojo majorPojo = majorMapper.getByMajorCode(majorCode).get(0);
        return Result.success(majorPojo);
    }

    @Override
    public Result<MajorPojo> getByMajorName(String majorName) {
        MajorPojo byMajorName = majorMapper.getByMajorName(majorName);
        return Result.success(byMajorName);
    }

    @Override
    public Result<List<MajorPojo>> getAll() {
        List<MajorPojo> majorPojos = majorMapper.getAll();
        return Result.success(majorPojos);
    }

    @Override
    public List<MajorIndexRequestPojo> majorIndexRequest() {
        return majorMapper.majorIndexRequest();
    }

    public void fill(MajorPojo majorPojo){
        FirstCoursePojo firstCoursePojo = firstCourseMapper.getByFirstCourseNo(majorPojo.getFirstCourseNo());
        SecondCoursePojo secondCoursePojo = secondCourseMapper.getById(majorPojo.getSecondCourseNo());
        majorPojo.setFirstCoursePojo(firstCoursePojo);
        majorPojo.setSecondCoursePojo(secondCoursePojo);
    }
    public void fill(List<MajorPojo> majorPojos){
        for (MajorPojo majorPojo:majorPojos) {
            fill(majorPojo);
        }
    }
    public static void main(String[] args) {
        System.out.println(DateUtil.today());
    }
}

package com.akz.ky.controller;

import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.CoursePojo;
import com.akz.ky.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 16:06
 * @Description
 */
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired(required = false)
    CourseService courseService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    Result add(@RequestBody CoursePojo coursePojo){
        boolean flag = courseService.add(coursePojo);
        if (!flag){
            return Result.failure(ApiReturnCode.C_Fail_Insert,"添加课程信息失败");
        }
        return Result.success(flag);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    Result delete(@RequestParam int id){
        boolean flag = courseService.delete(id);
        if (!flag){
            return Result.failure(ApiReturnCode.C_Fail_Delete,"删除课程信息失败");
        }
        return Result.success(flag);
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    Result update(@RequestBody CoursePojo coursePojo){
        boolean flag = courseService.update(coursePojo);
        if (!flag){
            return Result.failure(ApiReturnCode.C_Fail_Update,"更新课程信息失败");
        }
        return Result.success(flag);
    }

    @RequestMapping(value = "getById",method = RequestMethod.GET)
    Result<CoursePojo> getById(@RequestParam int id){
        return courseService.getById(id);
    }

    @RequestMapping(value = "getByName",method = RequestMethod.GET)
    Result<List<CoursePojo>> getByName(@RequestParam String name){
        return courseService.getByName(name);
    }

    @RequestMapping(value = "getByCode",method = RequestMethod.GET)
    Result<CoursePojo> getByCode(@RequestParam String code){
        return courseService.getByCode(code);
    }

    @RequestMapping(value = "listByType",method = RequestMethod.GET)
    Result<List<CoursePojo>> listByType(@RequestParam String type){
        return courseService.listByType(type);
    }

    @RequestMapping(value = "listByAll",method = RequestMethod.GET)
    Result<List<CoursePojo>> listByAll(){
        return courseService.listByAll();
    }

}

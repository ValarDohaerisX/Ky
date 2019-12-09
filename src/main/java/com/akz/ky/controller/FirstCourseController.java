package com.akz.ky.controller;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.FirstCoursePojo;
import com.akz.ky.service.FirstCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/10 12:53
 * @Description
 */
@RestController
@RequestMapping("/firstCourse")
public class FirstCourseController {
    @Autowired
    FirstCourseService firstCourseService;

    @RequestMapping(value = "addFirstCourse",method = RequestMethod.POST)
    Result add(@RequestBody FirstCoursePojo firstCoursePojo){
        return firstCourseService.add(firstCoursePojo);
    }

    @RequestMapping(value = "allFirstCourseList",method = RequestMethod.GET)
    Result<List<FirstCoursePojo>> listByAll(){
        return firstCourseService.listByAll();
    }

    @RequestMapping(value = "deleteFirstCourse",method = RequestMethod.DELETE)
    Result delete(@RequestParam int id){
        return firstCourseService.delete(id);
    }

    @RequestMapping(value = "updateFirstCourse",method = RequestMethod.PUT)
    Result updateFirstCourse(@RequestBody FirstCoursePojo firstCoursePojo){
        return firstCourseService.updateFirstCourse(firstCoursePojo);
    }
}

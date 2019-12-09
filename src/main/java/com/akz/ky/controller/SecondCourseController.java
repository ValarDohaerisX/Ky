package com.akz.ky.controller;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.SecondCoursePojo;
import com.akz.ky.service.SecondCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 11:12
 * @Description
 */
@RestController
@RequestMapping("secondCourse")
public class SecondCourseController {
    @Autowired(required = false)
    SecondCourseService secondCourseService;

    @RequestMapping(value = "getAll",method = RequestMethod.POST)
    Result<List<SecondCoursePojo>> getAll(){
        return secondCourseService.getAll();
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    Result<SecondCoursePojo> add(@RequestBody SecondCoursePojo secondCoursePojo){
        return secondCourseService.add(secondCoursePojo);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    Result update(@RequestBody SecondCoursePojo secondCoursePojo){
        return secondCourseService.update(secondCoursePojo);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    Result delete(@RequestParam int id){
        return secondCourseService.delete(id);
    }

    @RequestMapping(value = "getByName",method = RequestMethod.GET)
    Result<List<SecondCoursePojo>> getByName(@RequestParam String name){
        return secondCourseService.getByName(name);
    }

    @RequestMapping(value = "getByCode",method = RequestMethod.GET)
    Result<List<SecondCoursePojo>> getByLikeCode(@RequestParam String code){
        return secondCourseService.getByLikeCode(code);
    }

    @RequestMapping(value = "getByFirstCode",method = RequestMethod.POST)
    Result<List<SecondCoursePojo>> getByFirstCId(@RequestParam String firstCourseCode){
        return secondCourseService.getByFirstCourseCode(firstCourseCode);
    }
}

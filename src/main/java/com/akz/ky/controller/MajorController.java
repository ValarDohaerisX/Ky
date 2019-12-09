package com.akz.ky.controller;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.MajorPojo;
import com.akz.ky.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/6 14:52
 * @Description
 */
@RestController
@RequestMapping("major")
public class MajorController {
    @Autowired(required = false)
    MajorService majorService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result<MajorPojo> add(@RequestBody MajorPojo majorPojo){
        return majorService.add(majorPojo);
    }

    @RequestMapping(value ="/getMajor",method = RequestMethod.POST)
    public Result<List<MajorPojo>> getMajor(@RequestParam int firstCourseNo,@RequestParam int secondCourseNo){
        return majorService.getMajor(firstCourseNo,secondCourseNo);
    }

    @RequestMapping(value ="/updateMajor",method = RequestMethod.PUT)
    public Result updateMajor(@RequestBody MajorPojo majorPojo){
        return majorService.updateMajor(majorPojo);
    }

    @RequestMapping(value = "/deleteMajor",method = RequestMethod.DELETE)
    public Result deleteMajor(@RequestParam int majorNo){
        return majorService.deleteMajor(majorNo);
    }
}

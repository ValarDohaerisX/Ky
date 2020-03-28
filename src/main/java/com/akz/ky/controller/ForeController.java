package com.akz.ky.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.akz.ky.mapper.DepartmentMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.*;
import com.akz.ky.service.*;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/12 15:12
 * @Description
 */
@RestController
public class ForeController {

    @Autowired
    SchoolService schoolService;
    @Autowired(required = false)
    MajorService majorService;
    @Autowired(required = false)
    DepartmentMapper departmentMapper;
    @Autowired
    SchoolMainInfoService schoolMainInfoService;
    @Autowired
    LdcodeService ldcodeService;
    @Autowired
    FirstCourseService firstCourseService;
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Object search(String keyword){
        if (null == keyword)
            keyword = "";
        String s = checkKeywords(keyword);
        SchoolPojo body = null;
        MajorPojo majorPojo = null;
        Object o = null;
        SchoolDetailPojo schoolDetailPojo = new SchoolDetailPojo();
//        switch (s){
//            case "schoolCode" :
//                body = schoolService.getBySchoolCode(keyword).getBody();
//            case "majorCode" :
//                majorPojo = majorService.getByMajorCode(keyword).getBody();
//            case "majorName" :
//                majorPojo = majorService.getByMajorName(keyword).getBody();
//            case "schoolName" :
//                List<SchoolPojo> body3 = schoolService.getBySchoolNameLike(keyword).getBody();
//                if (body3!=null &&body3.size()>0){
//                    body = body3.get(0);
//                }
//        }
//        if (majorPojo != null){
//            o = majorPojo;
//        }
//        if (body != null){
//            o = body;
//        }
        //若搜索学校
        SchoolPojo schoolPojo = schoolService.getBySchoolNameLike(keyword).getBody().get(0);
        String schoolNo = String.valueOf(schoolPojo.getSchoolNo());
        List<DepartmentPojo> departmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        List<SchoolMainInfoPojo> schoolMainInfoPojos = schoolMainInfoService.getBySchoolNo(schoolNo).getBody();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = schoolService.getSchoolMainInfoMaps(schoolMainInfoPojos);
        List<List<SchoolMainInfoPojo>> schoolMainInfoPojoss = new ArrayList<>();
        for(Map.Entry<String, List<SchoolMainInfoPojo>> maps : schoolMainInfoMaps.entrySet()){
            if ("getStudent".equals(maps.getKey())){
                List<SchoolMainInfoPojo> schoolMainInfoPojos1 = new ArrayList<>();
                for (int i = 0; i < maps.getValue().size(); i++) {
                    if (i>=5)
                        break;
                    schoolMainInfoPojos1.add(maps.getValue().get(i));
                }
                schoolMainInfoPojoss.add(schoolMainInfoPojos1);
            }else {
                schoolMainInfoPojoss.add(maps.getValue());
            }
        }
        List<MajorPojo> majors = majorService.getMajorBySchoolNo(schoolPojo.getSchoolNo()).getBody();
        schoolPojo.setMajors(majors);
//        departmentPojos
        schoolDetailPojo.setSchoolNo(schoolNo);
        schoolDetailPojo.setSchoolName(schoolPojo.getSchoolName());
        schoolDetailPojo.setSchoolMainInfoPojos(schoolMainInfoPojoss);
        schoolDetailPojo.setSchoolPojo(schoolPojo);
        schoolDetailPojo.setDepartmentPojos(departmentPojos);
//        model.addAttribute("schoolPojo",schoolPojo);
//        model.addAttribute("a","aaaaa");
//        Map<String, Object> map = new HashMap<>();
//        map.put("schoolPojo",schoolPojo);
        return schoolDetailPojo;
    }

    @RequestMapping(value = "/schoolDetailInfo",method = RequestMethod.POST)
    public Object schoolDetailInfo(String schoolCode){
        if (null == schoolCode)
            schoolCode = "";
//        String s = checkKeywords(keyword);
        SchoolPojo body = null;
        MajorPojo majorPojo = null;
        Object o = null;
        SchoolDetailPojo schoolDetailPojo = new SchoolDetailPojo();
//        switch (s){
//            case "schoolCode" :
//                body = schoolService.getBySchoolCode(keyword).getBody();
//            case "majorCode" :
//                majorPojo = majorService.getByMajorCode(keyword).getBody();
//            case "majorName" :
//                majorPojo = majorService.getByMajorName(keyword).getBody();
//            case "schoolName" :
//                List<SchoolPojo> body3 = schoolService.getBySchoolNameLike(keyword).getBody();
//                if (body3!=null &&body3.size()>0){
//                    body = body3.get(0);
//                }
//        }
//        if (majorPojo != null){
//            o = majorPojo;
//        }
//        if (body != null){
//            o = body;
//        }
        //若搜索学校
        SchoolPojo schoolPojo = schoolService.getBySchoolCode(schoolCode).getBody();
        String schoolNo = String.valueOf(schoolPojo.getSchoolNo());
        List<DepartmentPojo> departmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        List<SchoolMainInfoPojo> schoolMainInfoPojos = schoolMainInfoService.getBySchoolNo(schoolNo).getBody();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = schoolService.getSchoolMainInfoMaps(schoolMainInfoPojos);
        List<List<SchoolMainInfoPojo>> schoolMainInfoPojoss = new ArrayList<>();
        for(Map.Entry<String, List<SchoolMainInfoPojo>> maps : schoolMainInfoMaps.entrySet()){
            if ("getStudent".equals(maps.getKey())){
                List<SchoolMainInfoPojo> schoolMainInfoPojos1 = new ArrayList<>();
                for (int i = 0; i < maps.getValue().size(); i++) {
                    if (i>=5)
                        break;
                    schoolMainInfoPojos1.add(maps.getValue().get(i));
                }
                schoolMainInfoPojoss.add(schoolMainInfoPojos1);
            }else {
                schoolMainInfoPojoss.add(maps.getValue());
            }
        }
        List<MajorPojo> majors = majorService.getMajorBySchoolNo(schoolPojo.getSchoolNo()).getBody();
        schoolPojo.setMajors(majors);
        List<DepartmentPojo> newPojos = new ArrayList<>();
        if (!dealDepAndMajors(departmentPojos,newPojos)){
            return Result.failure(ApiReturnCode.C_Fail,"专业信息查询失败");
        }
//        departmentPojos
        schoolDetailPojo.setSchoolNo(schoolNo);
        schoolDetailPojo.setSchoolName(schoolPojo.getSchoolName());
        schoolDetailPojo.setSchoolMainInfoPojos(schoolMainInfoPojoss);
        schoolDetailPojo.setSchoolPojo(schoolPojo);
        schoolDetailPojo.setDepartmentPojos(newPojos);
        return schoolDetailPojo;
    }

    @RequestMapping(value = "/schoolDetailInfoContent1",method = RequestMethod.POST)
    public Object schoolDetailInfoContent1(String schoolCode,String infoType,String infoNo){
        if (null == schoolCode)
            schoolCode = "";
//        String s = checkKeywords(keyword);
        SchoolPojo body = null;
        MajorPojo majorPojo = null;
        Object o = null;
        SchoolDetailPojo schoolDetailPojo = new SchoolDetailPojo();
//        switch (s){
//            case "schoolCode" :
//                body = schoolService.getBySchoolCode(keyword).getBody();
//            case "majorCode" :
//                majorPojo = majorService.getByMajorCode(keyword).getBody();
//            case "majorName" :
//                majorPojo = majorService.getByMajorName(keyword).getBody();
//            case "schoolName" :
//                List<SchoolPojo> body3 = schoolService.getBySchoolNameLike(keyword).getBody();
//                if (body3!=null &&body3.size()>0){
//                    body = body3.get(0);
//                }
//        }
//        if (majorPojo != null){
//            o = majorPojo;
//        }
//        if (body != null){
//            o = body;
//        }
        //若搜索学校
        SchoolPojo schoolPojo = schoolService.getBySchoolCode(schoolCode).getBody();
        String schoolNo = String.valueOf(schoolPojo.getSchoolNo());
        List<DepartmentPojo> departmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        List<SchoolMainInfoPojo> schoolMainInfoPojos = schoolMainInfoService.getBySchoolNo(schoolNo).getBody();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = schoolService.getSchoolMainInfoMaps(schoolMainInfoPojos);
        List<List<SchoolMainInfoPojo>> schoolMainInfoPojoss = new ArrayList<>();
        for(Map.Entry<String, List<SchoolMainInfoPojo>> maps : schoolMainInfoMaps.entrySet()){
            if ("getStudent".equals(maps.getKey())){
                List<SchoolMainInfoPojo> schoolMainInfoPojos1 = new ArrayList<>();
                for (int i = 0; i < maps.getValue().size(); i++) {
                    if (i>=5)
                        break;
                    schoolMainInfoPojos1.add(maps.getValue().get(i));
                }
                schoolMainInfoPojoss.add(schoolMainInfoPojos1);
            }else {
                schoolMainInfoPojoss.add(maps.getValue());
            }
        }
        List<MajorPojo> majors = majorService.getMajorBySchoolNo(schoolPojo.getSchoolNo()).getBody();
        schoolPojo.setMajors(majors);
        List<DepartmentPojo> newPojos = new ArrayList<>();
        if (!dealDepAndMajors(departmentPojos,newPojos)){
            return Result.failure(ApiReturnCode.C_Fail,"专业信息查询失败");
        }
//        departmentPojos
        schoolDetailPojo.setSchoolNo(schoolNo);
        schoolDetailPojo.setSchoolName(schoolPojo.getSchoolName());
        schoolDetailPojo.setSchoolMainInfoPojos(schoolMainInfoPojoss);
        schoolDetailPojo.setSchoolPojo(schoolPojo);
        schoolDetailPojo.setDepartmentPojos(newPojos);
        return schoolDetailPojo;
    }

    public  String checkKeywords(String keyword){
        String str = "";
        int length = keyword.length();
        boolean number = NumberUtil.isNumber(keyword);
        //如果关键字为纯数字
        if (number){
            if (length == 5){//5位即为院校代码
                str = "schoolCode";
            }else if(length == 6){ //6位即为专业代码
                str = "majorCode";
            }
        }else {//关键字为中文
            if ((keyword.indexOf("大学")==-1||keyword.indexOf("学院")==-1)){//关键字搜索专业
                str = "majorName";
            }else{//关键字搜索大学
                str = "schoolName";
            }
        }
        return str;
    }

    public boolean dealDepAndMajors(List<DepartmentPojo> departmentPojos,List<DepartmentPojo> newDepartmentPojos){
        LinkedHashMap<String, DepartmentPojo> objectObjectLinkedHashMap = new LinkedHashMap<>();
        for (DepartmentPojo departmentPojo:departmentPojos) {
            objectObjectLinkedHashMap.put(departmentPojo.getDepartmentName(),departmentPojo);
        }
        Set<Map.Entry<String, DepartmentPojo>> entries = objectObjectLinkedHashMap.entrySet();
        for (Map.Entry entry:entries) {
            newDepartmentPojos.add((DepartmentPojo) entry.getValue());
        }
        List<MajorPojo> majors;
        for (DepartmentPojo departmentPojo:newDepartmentPojos) {
            DepartmentPojo temp = new DepartmentPojo();
            majors = new ArrayList<>();
            for (DepartmentPojo departmentPojo1:departmentPojos) {
                if (departmentPojo.getDepartmentName().equals(departmentPojo1.getDepartmentName())){
                    String majorCode = departmentPojo1.getMajorCode();
                    MajorPojo majorPojo = majorService.getByMajorCode(majorCode).getBody();
                    majors.add(majorPojo);
                }
            }
            departmentPojo.setMajors(majors);
        }
        return true;
    }

    /**
     * 考研院校初始化接口
     * @return Object
     */
    @RequestMapping(value = "/initSchoolIndex",method = RequestMethod.POST)
    public Object initSchoolIndex(){
        Map<String,Object> returnData = new HashMap<>();
        //地区信息
        List<LdcodePojo> ldcodePojos = ldcodeService.getLdCodeByAddress();
        Map<String,List<LdcodePojo>> address = new HashMap<>();
        if (!dealAddressData(ldcodePojos,address)){
            return Result.failure(ApiReturnCode.C_Fail_Get,"地区信息查询失败");
        }
        //院校类型
        List<LdcodePojo> schoolTypes = ldcodeService.getLdCodeBySchoolType();
        //院校水平
        List<LdcodePojo> schoolLevels = ldcodeService.getLdCodeBySchoolLevel();
        //院校信息
        List<SchoolPojo> schoolPojos = schoolService.getAll().getBody();

        returnData.put("schoolPojos",schoolPojos);
        returnData.put("schoolTypes",schoolTypes);
        returnData.put("schoolLevels",schoolLevels);
        returnData.put("address",address);
        return returnData;
    }

    /**
     *  考研专业页面初始化接口
     * @return Object
     */
    @RequestMapping(value = "/initMajorIndex",method = RequestMethod.POST)
    public Object initMajorIndex(){
        Map<String,Object> params = new HashMap<>();
        //专业类型
        List<String> majorTypes = new ArrayList<>();
        majorTypes.add("不限");
        majorTypes.add("专业型硕士");
        majorTypes.add("学术型硕士");
        //学科门类
        List<FirstCoursePojo> firstCoursePojos = firstCourseService.listByAll().getBody();
        //所有专业
        List<MajorIndexRequestPojo> majorIndexRequestPojos = majorService.majorIndexRequest();
        LinkedHashMap<MajorIndexRequestPojo,List<MajorIndexRequestPojo>> majorIndexRequestMaps = new LinkedHashMap<>();

        List<SchoolPojo> schoolPojos = schoolService.getAll().getBody();
        List<MajorPojo> majorPojos = majorService.getAll().getBody();
        List<Map> majorIndexRequestLists = new ArrayList<>();
        if (!dealMajorIndexRequest(majorIndexRequestLists,majorIndexRequestMaps,majorIndexRequestPojos)){
            return Result.failure(ApiReturnCode.C_Fail_Get,"专业信息初始化失败");
        }
        params.put("majorTypes",majorTypes);
        params.put("firstCoursePojos",firstCoursePojos);
        params.put("majorIndexRequestLists",majorIndexRequestLists);
        return params;

    }

    private boolean dealMajorIndexRequest(List<Map> majorIndexRequestLists,LinkedHashMap<MajorIndexRequestPojo, List<MajorIndexRequestPojo>> majorIndexRequestMaps, List<MajorIndexRequestPojo> majorIndexRequestPojos) {
        if (majorIndexRequestPojos == null || majorIndexRequestPojos.size() == 0){
            return false;
        }

        for (MajorIndexRequestPojo majorIndexRequestPojo: majorIndexRequestPojos) {
            majorIndexRequestMaps.put(majorIndexRequestPojo,null);
        }
        for (Map.Entry<MajorIndexRequestPojo, List<MajorIndexRequestPojo>> map : majorIndexRequestMaps.entrySet()) {
            MajorIndexRequestPojo majorIndexRequestPojo = map.getKey();
            Map<String,Object> maps = new HashMap<>();
            List<MajorIndexRequestPojo> majorIndexRequestPojos1 = new ArrayList<>();
            for (MajorIndexRequestPojo majorIndexRequestPojo2: majorIndexRequestPojos) {
                if (majorIndexRequestPojo.getMajorCode().equals(majorIndexRequestPojo2.getMajorCode())){
                    maps.put("ZSMajor",majorIndexRequestPojo);
                    majorIndexRequestPojos1.add(majorIndexRequestPojo2);
                }
            }
            maps.put("TJSchools",majorIndexRequestPojos1);
            majorIndexRequestLists.add(maps);
        }

        return true;
    }


    private boolean dealAddressData(List<LdcodePojo> ldcodePojos, Map<String, List<LdcodePojo>> address) {
        address.put("全国",null);
        address.put("一区",null);
        address.put("二区",null);
        List<LdcodePojo> list0 = new ArrayList<>();
        List<LdcodePojo> list1 = new ArrayList<>();
        List<LdcodePojo> list2 = new ArrayList<>();
        for (LdcodePojo ldCodeByAddress : ldcodePojos) {
            int code  = Integer.parseInt(ldCodeByAddress.getCode());
            if (code>=1000&&code<2000){
                list1.add(ldCodeByAddress);
            }else if(code >2000){
                list2.add(ldCodeByAddress);
            }else if (code < 1000){
                list0.add(ldCodeByAddress);
            }
        }
        address.put("全国",list0);
        address.put("一区",list1);
        address.put("二区",list2);
        return true;
    }

    public static void main(String[] args) {
        System.out.println("111");
        Map<String,String> majorIndexRequestMaps = new HashMap<>();
        majorIndexRequestMaps.put("A","中国");
        majorIndexRequestMaps.put("A","郑州");
        majorIndexRequestMaps.put("B","美国");
        majorIndexRequestMaps.put("C","英国");
//        majorIndexRequestMaps.put("1",1);
//        majorIndexRequestMaps.put("1",1);
//        majorIndexRequestMaps.put("1",2);
//        majorIndexRequestMaps.put("1",3);
//        majorIndexRequestMaps.put("2",1);
//        majorIndexRequestMaps.put("2",1);
        System.out.println(majorIndexRequestMaps);
    }
}

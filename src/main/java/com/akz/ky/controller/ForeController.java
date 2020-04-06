package com.akz.ky.controller;

import cn.hutool.core.util.NumberUtil;
import com.akz.ky.mapper.DepartmentMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.*;
import com.akz.ky.service.*;
import com.akz.ky.utils.DateUtil;
import com.akz.ky.utils.JsonXMLUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/12 15:12
 * @Description 用户界面处理控制类
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
    @Autowired
    UserService userService;
    @Autowired(required = false)
    MainTieService mainTieService;
    @Autowired
    ChildTieService childTieService;
    public static DateUtil d = new DateUtil();
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

    /**
     * 用户注册接口
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object register(@RequestBody UserPojo user){
        String name = user.getUserLoginName();
        String pwd = user.getUserLoginPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setUserLoginName(name);
        boolean exists = userService.isExists(name);
        if (exists){
            return Result.failure(ApiReturnCode.C_Fail,"用户名已存在，请重新填写");
        }
//        注册时候的时候，会通过随机方式创建盐， 并且加密算法采用 "md5", 除此之外还会进行 2次加密。
//        这个盐，如果丢失了，就无法验证密码是否正确了，所以会数据库里保存起来。
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName,pwd,salt,times).toString();
        user.setUserLoginPassword(encodedPassword);
        user.setSalt(salt);
        user.setCreateDate(d.getCurrDate());
        user.setCreateTime(d.getCurrTime());
        user.setModifyDate(d.getCurrDate());
        user.setModifyTime(d.getCurrTime());
        userService.add(user);
        return Result.success(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody UserPojo user, HttpSession session){
        String name = user.getUserLoginName();
        String password = user.getUserLoginPassword();
        boolean exists = userService.isExists(name);
        if (!exists){
            return Result.failure(ApiReturnCode.C_Fail,"用户不存在！");
        }
//        登陆的时候， 通过 Shiro的方式进行校验
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        try{
            subject.login(token);
            UserPojo userPojo = userService.get(name);
            userPojo.setLastLoginDate(d.getCurrDate());
            userPojo.setModifyTime(d.getCurrTime());
            userPojo.setModifyDate(d.getCurrDate());
            userService.updateLoginTime(userPojo);
            session.setAttribute("user",userPojo);
            return Result.success(userPojo);
        }catch (AuthenticationException e){
            return Result.failure(ApiReturnCode.C_Fail,"密码错误，请重试！");
        }
    }
    @RequestMapping(value = "/exit",method = RequestMethod.POST)
    public Object exit(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
//        session.removeAttribute("user");
        return Result.success(ApiReturnCode.SUCCESS);
    }

    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST)
    public Object sendMsg(@RequestBody Map<String,Object> maps) throws Exception {
        System.out.println("maps:"+maps);
        UserPojo user = JsonXMLUtils.map2obj((Map<String, Object>) maps.get("user"),UserPojo.class);
        MainTiePojo tie = JsonXMLUtils.map2obj((Map<String, Object>) maps.get("msg"),MainTiePojo.class);
        System.out.println("send msg..");
        System.out.println("发帖内容:"+tie);
        System.out.println("发帖者:"+user);

        tie.setCreatedTime(d.getCurrDate()+" "+d.getCurrTime());
        tie.setUserNo(user.getUserNo());
        tie.setUserName(user.getUserName());
        System.out.println("组装消息体:"+tie);
        mainTieService.add(tie);
        return Result.success(tie);
    }

    @RequestMapping(value = "/getMsg",method = RequestMethod.POST)
    public Object getMsg(@RequestBody UserPojo userPojo){
        HashMap<String,Object> maps = new HashMap<>();
        List<MainTiePojo> ties = mainTieService.getMainTie();
        List<ChildTiePojo> childTiePojos = childTieService.getAll();
        HashMap<Integer,HashMap<Integer,List<List<ChildTiePojo>>>> map = new HashMap<>();
        dealChildTiePojoClass(map,childTiePojos);
        maps.put("msgs",ties);
        maps.put("childMsgs",map);
        return Result.success(maps);
    }

    private void dealChildTiePojoClass(HashMap<Integer, HashMap<Integer,List<List<ChildTiePojo>>>> maps, List<ChildTiePojo> childTiePojos) {
        HashMap<Integer,List<List<ChildTiePojo>>> map = new HashMap<>();
        for (ChildTiePojo childTiePojo:childTiePojos) {
            List<List<ChildTiePojo>> splitAllMsgs = new ArrayList<>();//分离后总的评论及回复
            List<ChildTiePojo> childTiePojos1 = new ArrayList<>();
            List<ChildTiePojo> childTiePojos2 = new ArrayList<>();
            if (0!=childTiePojo.getChildTieNoo()){
                continue;
            }
            for (ChildTiePojo childTiePojo1:childTiePojos) {
                if (childTiePojo.getChildTieNo()==childTiePojo1.getChildTieNo() && childTiePojo1.getChildTieNoo() == 0){
                    childTiePojos1.add(childTiePojo1);
                }
                if (childTiePojo.getChildTieNo()==childTiePojo1.getChildTieNoo() && childTiePojo1.getChildTieNoo() != 0){
                    childTiePojos2.add(childTiePojo1);
                }
            }
            splitAllMsgs.add(childTiePojos1);
            splitAllMsgs.add(childTiePojos2);
            map.put(childTiePojo.getChildTieNo(),splitAllMsgs);
            maps.put(childTiePojo.getMainTieNo(),map);
        }
    }

    @RequestMapping(value = "/sendChildTie",method =  RequestMethod.POST)
    public Object sendChildTie(@RequestBody ChildTiePojo childTiePojo){
        if (childTiePojo == null)
            return Result.failure(ApiReturnCode.C_Fail_Insert,"后台未成功获取数据！");
        int mainTieNo = childTiePojo.getMainTieNo();
        MainTiePojo mainTiePojo = mainTieService.getOne(mainTieNo);
        mainTiePojo.setLastReplyTime(d.getCurrDate()+" "+d.getCurrTime());
        mainTiePojo.setReviewNum(mainTiePojo.getReviewNum()+1);
        mainTieService.update(mainTiePojo);
        childTiePojo.setCreatedTime(d.getCurrDate()+" "+d.getCurrTime());
        childTieService.add(childTiePojo);
        return Result.success(childTiePojo);
    }
    public static void main(String[] args) {
        System.out.println("111");
        Map<String,String> majorIndexRequestMaps = new HashMap<>();
        majorIndexRequestMaps.put("A","中国");
        majorIndexRequestMaps.put("A","郑州");
        majorIndexRequestMaps.put("B","美国");
        majorIndexRequestMaps.put("C","英国");
        System.out.println("t:"+new Timestamp(new Date().getTime()));
//        majorIndexRequestMaps.put("1",1);
//        majorIndexRequestMaps.put("1",1);
//        majorIndexRequestMaps.put("1",2);
//        majorIndexRequestMaps.put("1",3);
//        majorIndexRequestMaps.put("2",1);
//        majorIndexRequestMaps.put("2",1);
        System.out.println(majorIndexRequestMaps);
    }
}

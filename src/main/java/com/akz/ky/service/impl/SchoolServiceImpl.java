package com.akz.ky.service.impl;

import com.akz.ky.mapper.*;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.*;
import com.akz.ky.service.SchoolService;
import com.akz.ky.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 14:23
 * @Description
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired(required = false)
    SchoolMapper schoolMapper;
    @Autowired(required = false)
    SchoolMainInfoMapper schoolMainInfoMapper;
    @Autowired(required = false)
    DepartmentMapper departmentMapper;
    @Autowired(required = false)
    MajorMapper majorMapper;
    @Autowired(required = false)
    SecondCourseMapper secondCourseMapper;
    @Autowired(required = false)
    FirstCourseMapper firstCourseMapper;

    private static DateUtil dateUtil = new DateUtil();

    @Override
    public Result add(SchoolPojo schoolPojo) {
        if (schoolPojo == null)
            return Result.failure(ApiReturnCode.C_Fail);
        String schoolLevel = schoolPojo.getSchoolLevel();
        schoolLevel = getFormatString(schoolLevel);
        schoolPojo.setSchoolLevel(schoolLevel);
        boolean flag = schoolMapper.add(schoolPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Insert);
        return Result.success(flag);
    }

    @Override
    public Result update(SchoolPojo schoolPojo) {
        if (schoolPojo == null)
            return Result.failure(ApiReturnCode.C_Fail);
        boolean flag = schoolMapper.update(schoolPojo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Update);
        return Result.success(flag);
    }

    @Override
    public Result<SchoolPojo> getBySchoolNo(String schoolNo) {
        SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo(schoolNo);
        if (bySchoolNo == null)
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据不存在");
        return Result.success(bySchoolNo);
    }

    @Override
    public Result<List<SchoolPojo>> getAll() {
        List<SchoolPojo> all = schoolMapper.getAll();
        if (all == null || all.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get,"数据为空");
        }
        return Result.success(all);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolNameLike(String schoolName) {
        List<SchoolPojo> bySchoolNameLike = schoolMapper.getBySchoolNameLike(schoolName);
        if (bySchoolNameLike == null || bySchoolNameLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolNameLike);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolTypeLike(String schoolType) {
        List<SchoolPojo> bySchoolTypeLike = schoolMapper.getBySchoolTypeLike(schoolType);
        if (bySchoolTypeLike == null || bySchoolTypeLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolTypeLike);
    }

    @Override
    public Result<List<SchoolPojo>> getBySchoolLevelLike(String schoolLevel) {
        List<SchoolPojo> bySchoolLevelLike = schoolMapper.getBySchoolLevelLike(schoolLevel);
        if (bySchoolLevelLike == null || bySchoolLevelLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(bySchoolLevelLike);
    }

    @Override
    public Result<List<SchoolPojo>> getByAddressLike(String address) {
        List<SchoolPojo> byAddressLike = schoolMapper.getByAddressLike(address);
        checkIfEmpty(byAddressLike);
        return Result.success(byAddressLike);
    }

    @Override
    public Result delete(String schoolNo) {
        boolean flag = schoolMapper.delete(schoolNo);
        if (!flag)
            return Result.failure(ApiReturnCode.C_Fail_Delete);
        return Result.success(flag);
    }

    @Override
    public Result<SchoolDetailPojo> getSchoolDetailInfo(String schoolNo) {
        SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo(schoolNo);
        SchoolDetailPojo schoolDetailPojo = new SchoolDetailPojo();
        if (bySchoolNo == null)
            return Result.failure(ApiReturnCode.C_Fail_Get,"暂无该院校基本信息");
        List<SchoolMainInfoPojo> schoolMainInfoPojos = schoolMainInfoMapper.getBySchoolNo(schoolNo);
//        if (schoolMainInfoPojos == null || schoolMainInfoPojos.size()==0)
//            return Result.failure(ApiReturnCode.C_Fail_Get,"暂无该院校主要信息");
        List<DepartmentPojo> departmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        schoolDetailPojo.setDepartmentPojos(departmentPojos);
        schoolDetailPojo.setSchoolNo(schoolNo);
        schoolDetailPojo.setSchoolName(bySchoolNo.getSchoolName());
        schoolDetailPojo.setSchoolMainInfoPojos(schoolMainInfoPojos);
        schoolDetailPojo.setSchoolPojo(bySchoolNo);
        return Result.success(schoolDetailPojo);
    }

    @Override
    public Result setSchoolDetailInfo(SchoolDetailPojo newSchoolDetailPojo) {
        if (newSchoolDetailPojo == null)
            return Result.failure(ApiReturnCode.C_Fail,"数据传入后台为空，请检查");
        SchoolPojo newSchoolPojo = newSchoolDetailPojo.getSchoolPojo();
        List<DepartmentPojo> newDepartmentPojos = newSchoolDetailPojo.getDepartmentPojos();
        String schoolNo = newSchoolDetailPojo.getSchoolNo();
        Result<SchoolDetailPojo> oriInfo = getSchoolDetailInfo(schoolNo);
        /**
         *  匹配院校基本信息中的院校简介，内容若不同则更新简介信息 modify by lzx 2020/1/15
         */
        if ("1000".equals(oriInfo.getReturnCode())){
            SchoolDetailPojo oriSchoolDetailPojo = oriInfo.getBody();
            SchoolPojo oriSchoolPojo = oriSchoolDetailPojo.getSchoolPojo();
            if (!oriSchoolPojo.getSchoolInfo().equals(newSchoolPojo.getSchoolInfo())){
                boolean flag = schoolMapper.update(newSchoolPojo);
                if (flag == false)
                    return Result.failure(ApiReturnCode.C_Fail_Insert,"院校基本信息更新失败");
            }
            /**
             *  匹配院校明细信息，对招生院系及以下的专业信息逐一匹配，不同则更新 modify by lzx 2020/1/15
             */
            //页面更新院系信息以及更新院系下的专业信息
            List<DepartmentPojo> oriDepartmentPojos = oriSchoolDetailPojo.getDepartmentPojos();
            if (oriDepartmentPojos.size() == newDepartmentPojos.size()){
                //新老院系信息集合进行比对- 目前院系信息结构是一条院系信息对应一条专业信息(majorNo,majorCode,majorName,majorType)!   **
                for (DepartmentPojo oriDepartmentPojo : oriDepartmentPojos){
                    for (DepartmentPojo newDepartmentPojo : newDepartmentPojos){
                        //处理院系名相同的院系信息
                        if (oriDepartmentPojo.getDepartmentName() == newDepartmentPojo.getDepartmentName()){
                            //处理存在修改的院系信息
                            if (!oriDepartmentPojo.equals(newDepartmentPojo)){
                                //新增院系信息
//                                if ("".equals(oriDepartmentPojo.getDepartmentNo())||oriDepartmentPojo.getDepartmentNo() == null){
//
//                                }
                                //更新院系名称信息
                                if (!oriDepartmentPojo.getDepartmentName().equals(newDepartmentPojo.getDepartmentName())){
                                    boolean flag = departmentMapper.update(newDepartmentPojo);
                                    if (flag == false)
                                        return Result.failure(ApiReturnCode.C_Fail_Update);
                                }
                                //处理专业号相同的专业信息
                                int oriDepartmentPojoMajorNo = oriDepartmentPojo.getMajorNo();
                                int newDepartmentPojoMajorNo = newDepartmentPojo.getMajorNo();
                                if (oriDepartmentPojoMajorNo == newDepartmentPojoMajorNo) {
                                    String oriDepartmentPojoMajorCode = oriDepartmentPojo.getMajorCode();
                                    String newDepartmentPojoMajorCode = newDepartmentPojo.getMajorCode();
                                    if (oriDepartmentPojoMajorCode != newDepartmentPojoMajorCode) {
                                        MajorPojo majorPojo = majorMapper.getByMajorNo(oriDepartmentPojo.getMajorNo());
                                        majorPojo.setMajorCode(newDepartmentPojoMajorCode);
                                        majorPojo.setMajorName(newDepartmentPojo.getMajorName());
                                        boolean flag = majorMapper.updateMajor(majorPojo);
                                        String departmentName = newDepartmentPojo.getDepartmentName();
                                        if (flag == false)
                                            return Result.failure(ApiReturnCode.C_Fail_Update,"[ "+departmentName+" ] 下专业信息更新失败...");
                                    }
                                }
                            }else {
                                // do nothing
                            }
                            //更新
//                            oriDepartmentPojo.get
                        }
                    }
                }
            }else if (oriDepartmentPojos.size() == 0){
                //若数据库中无数据，则直接插入
                //新增院系信息
                List<String> errorMajorCodeList = new ArrayList<>();
                addDepartmentInfo(newDepartmentPojos,errorMajorCodeList);
            } else if(oriDepartmentPojos.size() > newDepartmentPojos.size()) {
                dealDepartmentInfo(oriDepartmentPojos,newDepartmentPojos);
            }else if(oriDepartmentPojos.size() < newDepartmentPojos.size()){
                dealDepartmentInfo(oriDepartmentPojos,newDepartmentPojos);
            }
            /**
             *  匹配院校明细信息，对公告信息，招生简章 , 调剂信息逐一匹配，不同则更新 modify by lzx 2020/1/15
             */
            List<SchoolMainInfoPojo> oriSchoolDetailPojoSchoolMainInfoPojos = oriSchoolDetailPojo.getSchoolMainInfoPojos();
            List<SchoolMainInfoPojo> newSchoolDetailPojoSchoolMainInfoPojos = newSchoolDetailPojo.getSchoolMainInfoPojos();
            if (dealSchoolMainInfo(oriSchoolDetailPojoSchoolMainInfoPojos,newSchoolDetailPojoSchoolMainInfoPojos)){
                return Result.failure(ApiReturnCode.C_Fail);
            }
//            if (ori)
        }
        return null;
    }

    private boolean dealDepartmentInfo(List<DepartmentPojo> oriDepartmentPojos, List<DepartmentPojo> newDepartmentPojos) {
        for (DepartmentPojo oriDepartmentPojo:oriDepartmentPojos) {
            for (DepartmentPojo newDepartmentPojo:newDepartmentPojos) {
                int oriDepartmentId = oriDepartmentPojo.getDepartmentId();
                int newDepartmentId = newDepartmentPojo.getDepartmentId();
                if (oriDepartmentId == newDepartmentId){
                    oriDepartmentPojos.remove(oriDepartmentPojo);
                    newDepartmentPojos.remove(newDepartmentPojo);
                    if (!newDepartmentPojo.getMajorCode().equals(oriDepartmentPojo.getMajorCode())||
                            !newDepartmentPojo.getDepartmentName().equals(oriDepartmentPojo.getDepartmentName())||
                            newDepartmentPojo.getMajorName().equals(oriDepartmentPojo.getMajorName())){
                        boolean update = departmentMapper.update(newDepartmentPojo);
                        if (update == false)
                            return false;
                    }
                    break;
                }
            }
        }
        //当前经过处理后的两个集合，ori是需要删除的，new是添加的
        for (DepartmentPojo departmentPojo:newDepartmentPojos){
            boolean add = departmentMapper.add(departmentPojo);
            if (add == false)
                return false;
        }
        //当前经过处理后的两个集合，ori是需要删除的，new是添加的
        for (DepartmentPojo departmentPojo:oriDepartmentPojos){
            boolean add = departmentMapper.deleteDepartmentId(departmentPojo.getDepartmentId());
            if (add == false)
                return false;
        }
        return true;
    }

    private boolean addDepartmentInfo(List<DepartmentPojo> newDepartmentPojos,List<String> errorMajorCodeList) {
        for (DepartmentPojo department : newDepartmentPojos) {
            if (department.getMajorCode().length() != 6){
                errorMajorCodeList.add("专业代码: "+department.getMajorCode()+",专业名称: "+department.getMajorName());
                continue;
            }
            int i = 1;
            department.setDepartmentNo("00"+(i++));
            String firstCourseCode = department.getMajorCode().substring(0, 2);
            String secondCorseCode = department.getMajorCode().substring(0, 4);
            int firstCourseNo = firstCourseMapper.getByCode(firstCourseCode).getFirstCourseNo();
            int secondCourseNo = secondCourseMapper.getByCode(secondCorseCode).getSecondCourseNo();
            MajorPojo majorPojo = new MajorPojo();
            majorPojo.setMajorName(department.getMajorName());
            majorPojo.setMajorType(department.getMajorType());
            majorPojo.setMajorCode(department.getMajorCode());
            majorPojo.setFirstCourseNo(firstCourseNo);
            majorPojo.setSecondCourseNo(secondCourseNo);
            majorPojo.setCurrYear(dateUtil.getCurrDate());
            boolean f1 = majorMapper.add(majorPojo);
            boolean f2 = departmentMapper.add(department);
            if (f1 == false || f2 == false){
                return false;
            }
        }
        return true;
    }

    private boolean dealSchoolMainInfo(List<SchoolMainInfoPojo> oriSchoolDetailPojoSchoolMainInfoPojos, List<SchoolMainInfoPojo> newSchoolDetailPojoSchoolMainInfoPojos) {
        if (oriSchoolDetailPojoSchoolMainInfoPojos.size() == 0) {
            //若数据库中没有此学校的明细信息，则直接插入数据
            for (SchoolMainInfoPojo schoolMainInfoPojo:newSchoolDetailPojoSchoolMainInfoPojos) {
                schoolMainInfoPojo.setCreateDate(dateUtil.getCurrDate(DateUtil.formatPattern2));
                schoolMainInfoPojo.setCreateTime(dateUtil.getCurrDate(DateUtil.formatPattern3));
                schoolMainInfoPojo.setModifyDate(dateUtil.getCurrDate(DateUtil.formatPattern2));
                schoolMainInfoPojo.setModifyTime(dateUtil.getCurrDate(DateUtil.formatPattern3));
                boolean flag = schoolMainInfoMapper.add(schoolMainInfoPojo);
                if (flag == false)
                    return false;
            }
        }else{
            //若数据库中存在该学校的明细信息，则遍历处理信息
            //获取分类好的信息map集合
            Map<String, List<SchoolMainInfoPojo>> oriSchoolMainInfoMaps = getSchoolMainInfoMaps(oriSchoolDetailPojoSchoolMainInfoPojos);
            LinkedHashMap<String, List<SchoolMainInfoPojo>> newSchoolMainInfoMaps = getSchoolMainInfoMaps(newSchoolDetailPojoSchoolMainInfoPojos);
            for (Map.Entry<String, List<SchoolMainInfoPojo>> oriEntry : oriSchoolMainInfoMaps.entrySet()) {
                for (Map.Entry<String, List<SchoolMainInfoPojo>> newEntry : newSchoolMainInfoMaps.entrySet()) {
                    //相同信息类型的map集合
                    if (oriEntry.getKey().equals(newEntry.getKey())){
                        List<SchoolMainInfoPojo> oriEntryValue = oriEntry.getValue();
                        List<SchoolMainInfoPojo> newEntryValue = newEntry.getValue();
                        //1.先将信息号为空数据直接插入数据库，并且在map中移除该数据
                        for (SchoolMainInfoPojo sc:newEntryValue){
                            if (String.valueOf(sc.getInfoNo())== null){
                                setSchoolMainInfoDateTime(sc);
                                boolean add = schoolMainInfoMapper.add(sc);
                                if (add == false)
                                    return false;
                                newEntryValue.remove(sc);
                            }
                        }
                        //2.再将剩下的数据进行处理,剩下的数据都是有信息号的,根据信息号进行处理
                        //两个信息集合数量相等则更新信息集合
                        if (oriEntryValue.size() == newEntryValue.size()){
                            for (SchoolMainInfoPojo orischoolMainInfoPojo:oriEntryValue) {
                                for (SchoolMainInfoPojo newschoolMainInfoPojo:newEntryValue) {
                                    if (orischoolMainInfoPojo.getInfoNo() == newschoolMainInfoPojo.getInfoNo()){
                                        if (!orischoolMainInfoPojo.getInfoTitle().equals(newschoolMainInfoPojo.getInfoTitle())||!orischoolMainInfoPojo.getInfoContent().equals(newschoolMainInfoPojo.getInfoContent())){
                                            setSchoolMainInfoModifyDateTime(newschoolMainInfoPojo);
                                            boolean update = schoolMainInfoMapper.update(newschoolMainInfoPojo);
                                            if (update == false)
                                                return false;
                                        }
                                    }
                                }
                            }
                        }else if (oriEntryValue.size() > newEntryValue.size()){
                            //当根据信息号匹配到的时候，更新数据，并将数据移除map
                            dealMatchSchoolMainInfo(oriEntryValue,newEntryValue);
                        }else if (oriEntryValue.size() < newEntryValue.size()){
                            dealMatchSchoolMainInfo(oriEntryValue,newEntryValue);
                        }else {
                            //do nothing
                        }
                    }
                }
            }
        }
        return true;
    }
    //处理匹配到的院校明细信息
    private boolean dealMatchSchoolMainInfo(List<SchoolMainInfoPojo> oriEntryValue, List<SchoolMainInfoPojo> newEntryValue) {
        for (SchoolMainInfoPojo orischoolMainInfoPojo:oriEntryValue) {
            for (SchoolMainInfoPojo newschoolMainInfoPojo:newEntryValue) {
                if (orischoolMainInfoPojo.getInfoNo() == newschoolMainInfoPojo.getInfoNo()){
                    if (!orischoolMainInfoPojo.getInfoTitle().equals(newschoolMainInfoPojo.getInfoTitle())
                            ||!orischoolMainInfoPojo.getInfoContent().equals(newschoolMainInfoPojo.getInfoContent())){
                        setSchoolMainInfoModifyDateTime(newschoolMainInfoPojo);
                        boolean update = schoolMainInfoMapper.update(newschoolMainInfoPojo);
                        if (update == false)
                            return false;
                        oriEntryValue.remove(orischoolMainInfoPojo);
                        newEntryValue.remove(newschoolMainInfoPojo);
                        break;
                    }
                }
            }
        }
        //全部遍历后，剩下的ori都是需要删除的，剩下的new都是需要添加的,然后批量操作这些数据
        for (SchoolMainInfoPojo newschoolMainInfoPojo:newEntryValue) {
            boolean update = schoolMainInfoMapper.update(newschoolMainInfoPojo);
            if (update == false)
                return false;
        }
        //全部遍历后，剩下的ori都是需要删除的，剩下的new都是需要添加的,然后批量操作这些数据
        for (SchoolMainInfoPojo orischoolMainInfoPojo:newEntryValue) {
            boolean update = schoolMainInfoMapper.delete(String.valueOf(orischoolMainInfoPojo.getInfoNo()));
            if (update == false)
                return false;
        }
        return true;
    }

    private void setSchoolMainInfoModifyDateTime(SchoolMainInfoPojo newschoolMainInfoPojo) {
        newschoolMainInfoPojo.setModifyDate(dateUtil.getCurrDate(DateUtil.formatPattern2));
        newschoolMainInfoPojo.setModifyTime(dateUtil.getCurrDate(DateUtil.formatPattern3));
    }

    private void setSchoolMainInfoDateTime(SchoolMainInfoPojo schoolMainInfoPojo) {
        schoolMainInfoPojo.setCreateDate(dateUtil.getCurrDate(DateUtil.formatPattern2));
        schoolMainInfoPojo.setCreateTime(dateUtil.getCurrDate(DateUtil.formatPattern3));
        setSchoolMainInfoModifyDateTime(schoolMainInfoPojo);
    }

    //根据信息类型将信息集合进行分类存放到LinkedHashMap(有序)中,
    public LinkedHashMap<String,List<SchoolMainInfoPojo>> getSchoolMainInfoMaps(List<SchoolMainInfoPojo> schoolDetailPojoSchoolMainInfoPojos){
        LinkedHashMap<String,List<SchoolMainInfoPojo>> oriSchoolMainInfoPojoMaps = new LinkedHashMap<>();
        if (schoolDetailPojoSchoolMainInfoPojos!=null){
            for (SchoolMainInfoPojo schoolMainInfoPojo:schoolDetailPojoSchoolMainInfoPojos) {
                if (oriSchoolMainInfoPojoMaps.containsKey(schoolMainInfoPojo.getInfoType())){
                    oriSchoolMainInfoPojoMaps.get(schoolMainInfoPojo.getInfoType()).add(schoolMainInfoPojo);
                }else {
                    ArrayList<SchoolMainInfoPojo> tempOriSchoolMainInfoPojos = new ArrayList<>();
                    tempOriSchoolMainInfoPojos.add(schoolMainInfoPojo);
                    oriSchoolMainInfoPojoMaps.put(schoolMainInfoPojo.getInfoType().toString(),tempOriSchoolMainInfoPojos);
                }
            }
        }
        return oriSchoolMainInfoPojoMaps;
    }
    public Result checkIfEmpty(List<SchoolPojo> schoolPojos){
        if (schoolPojos == null || schoolPojos.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get);
        }
        return Result.success(ApiReturnCode.SUCCESS);
    }
    //将获取到的院校水平信息格式如 ["一流学科","985高校","211高校"] 转换成 一流学科|985高校|211高校
    public String getFormatString(String s){
        char[] chars = s.toCharArray();
        String tempStr = "";
        for (char c:chars){
            tempStr+=(('['!=c&&'\"'!=c&&']'!=c)?((c==','?'|':c)):"");
        }
        return tempStr;
    }

    public static void main(String[] args) {
        String str = "123456";
        System.out.println(str.substring(0,4));
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}

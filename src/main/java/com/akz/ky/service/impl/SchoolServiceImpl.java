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

import static javafx.scene.input.KeyCode.T;

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
        List<List<SchoolMainInfoPojo>> schoolMainInfoPojoss = new ArrayList<>();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = getSchoolMainInfoMaps(schoolMainInfoPojos);
        int i = 0;
        for(Map.Entry<String, List<SchoolMainInfoPojo>> maps : schoolMainInfoMaps.entrySet()){
            System.out.println(maps.getKey()+"\n"+maps.getValue());
            schoolMainInfoPojoss.add(maps.getValue());
            ++i;
        }
        System.out.println("往List<List<SchoolMainInfoPojo>>添加集合循环了 "+ i +"次。");
//        if (schoolMainInfoPojos == null || schoolMainInfoPojos.size()==0)
//            return Result.failure(ApiReturnCode.C_Fail_Get,"暂无该院校主要信息");
        List<DepartmentPojo> departmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        schoolDetailPojo.setDepartmentPojos(departmentPojos);
        schoolDetailPojo.setSchoolNo(schoolNo);
        schoolDetailPojo.setSchoolName(bySchoolNo.getSchoolName());
        schoolDetailPojo.setSchoolMainInfoPojos(schoolMainInfoPojoss);
        schoolDetailPojo.setSchoolPojo(bySchoolNo);
        return Result.success(schoolDetailPojo);
    }
    @Override
    public Result setSchoolDetailInfo(SchoolDetailRequestPojo schoolDetailRequestPojo) {
        SchoolDetailPojo newSchoolDetailPojo = new SchoolDetailPojo();
        SchoolPojo newSchoolPojo = schoolDetailRequestPojo.getSchoolPojo();
        String infoContent = schoolDetailRequestPojo.getDescribeForm().getInfoContent();
        //处理院校基本信息-判断学校简介信息是否发生改变-School
        String schoolNo = schoolDetailRequestPojo.getSchoolNo();
        newSchoolDetailPojo.setSchoolNo(schoolNo);
        newSchoolDetailPojo.setSchoolName(newSchoolPojo.getSchoolName());
        newSchoolDetailPojo.setDepartmentPojos(schoolDetailRequestPojo.getDepartmentForm());
        SchoolPojo oriSchoolPojo = schoolMapper.getBySchoolNo(schoolNo);
        if (oriSchoolPojo.getSchoolInfo() == null||"".equals(oriSchoolPojo.getSchoolInfo())||!oriSchoolPojo.getSchoolInfo().equals(infoContent)){
            newSchoolPojo.setSchoolInfo(infoContent);
            boolean flag = schoolMapper.update(newSchoolPojo);
            if (!flag){
                return Result.failure(ApiReturnCode.C_Fail_Update,"学校简介信息更新失败");
            }
        }

        /**
         *  匹配院校明细信息，对公告信息，招生简章 , 调剂信息逐一匹配，不同则更新 modify by lzx 2020/1/15
         */
        List<SchoolMainInfoPojo> oriSchoolMainInfoPojo = schoolMainInfoMapper.getBySchoolNo(schoolNo);
        List<SchoolMainInfoPojo> newSchoolMainInfoPojo = new ArrayList<>();
        List<SchoolMainInfoPojo> describeForms = new ArrayList<>();
        List<TitleForm> titleForm = schoolDetailRequestPojo.getTitleForm();
        List<GetStudentForm> newGetStudentForm = schoolDetailRequestPojo.getGetStudentForm();
        DescribeForm describeForm = schoolDetailRequestPojo.getDescribeForm();
        List<DispensingForm> dispensingForm = schoolDetailRequestPojo.getDispensingForm();
        List<ScholarshipForm> scholarshipForm = schoolDetailRequestPojo.getScholarshipForm();
        //将请求到的数据统一封装SchoolDetailPojo内
        describeForms.add(describeForm);
        //往newSchoolDetailPojo.getSchoolMainInfoPojos()里添加请求的数据
        addSchoolMainInfoPojoss(newSchoolDetailPojo,newSchoolMainInfoPojo,newGetStudentForm,describeForms,dispensingForm,scholarshipForm,titleForm);

        if(!dealSchoolMainInfo(oriSchoolMainInfoPojo,newSchoolMainInfoPojo,schoolNo)){
            return Result.failure(ApiReturnCode.C_Fail,"院校明细信息处理失败");
        }

        //处理院系信息-department
        List<DepartmentPojo> newDepartmentPojos = schoolDetailRequestPojo.getDepartmentForm();
        List<DepartmentPojo> oriDepartmentPojos = departmentMapper.getBySchoolNo(schoolNo);
        if (!dealDepartmentInfo(oriDepartmentPojos,newDepartmentPojos,schoolNo)){
            return Result.failure(ApiReturnCode.C_Fail,"院系信息处理失败");
        }
        return Result.success(schoolDetailRequestPojo);
    }

    //往newSchoolDetailPojo.getSchoolMainInfoPojos()里添加请求的数据
    private void addSchoolMainInfoPojoss(SchoolDetailPojo newSchoolDetailPojo, List<SchoolMainInfoPojo> newSchoolMainInfoPojo, List<GetStudentForm> newGetStudentForm, List<SchoolMainInfoPojo> describeForms, List<DispensingForm> dispensingForm, List<ScholarshipForm> scholarshipForm, List<TitleForm> titleForm) {
        List<List<SchoolMainInfoPojo>> sc = new ArrayList<>();
        SchoolMainInfoPojo schoolMainInfoPojo = null;
        List<SchoolMainInfoPojo> schoolMainInfoPojoGetStu = new ArrayList<>();
        List<SchoolMainInfoPojo> schoolMainInfoPojoTitle = new ArrayList<>();
        List<SchoolMainInfoPojo> schoolMainInfoPojoDispensing = new ArrayList<>();
        List<SchoolMainInfoPojo> schoolMainInfoPojoDescribe = new ArrayList<>();
        if (newGetStudentForm.size()>0) {
            for (GetStudentForm getStudentForm : newGetStudentForm) {
                if ("".equals(getStudentForm.getInfoTitle()) ||"".equals(getStudentForm.getInfoContent())){
                    continue;
                }
                schoolMainInfoPojo = new SchoolMainInfoPojo();
                schoolMainInfoPojo.setSchoolNo(getStudentForm.getSchoolNo());
                schoolMainInfoPojo.setInfoNo(getStudentForm.getInfoNo());
                schoolMainInfoPojo.setInfoTitle(getStudentForm.getInfoTitle());
                schoolMainInfoPojo.setInfoType(getStudentForm.getInfoType());
                schoolMainInfoPojo.setInfoContent(getStudentForm.getInfoContent());
                schoolMainInfoPojo.setCreateTime(getStudentForm.getCreateTime());
                schoolMainInfoPojo.setCreateDate(getStudentForm.getCreateDate());
                schoolMainInfoPojo.setLastModifyDate(getStudentForm.getLastModifyDate());
                schoolMainInfoPojo.setLastModifyTime(getStudentForm.getLastModifyTime());
                newSchoolMainInfoPojo.add(schoolMainInfoPojo);
                schoolMainInfoPojoGetStu.add(schoolMainInfoPojo);
            }
            if (schoolMainInfoPojoGetStu.size()>0){
                sc.add(schoolMainInfoPojoGetStu);
            }
        }

        if (describeForms.size()>0){
            if (!"".equals(describeForms.get(0).getInfoTitle()) || !"".equals(describeForms.get(0).getInfoContent())) {
                for (SchoolMainInfoPojo schoolMainInfoPojo1 : describeForms) {
                    newSchoolMainInfoPojo.add(schoolMainInfoPojo1);
                    schoolMainInfoPojoDescribe.add(schoolMainInfoPojo1);
                }
                sc.add(schoolMainInfoPojoDescribe);
            }
        }

        if (dispensingForm.size()>0) {
            for (DispensingForm dispensingForm1 : dispensingForm) {
                if ("".equals(dispensingForm1.getInfoTitle()) || "".equals(dispensingForm1.getInfoContent())) {
                    continue;
                }
                schoolMainInfoPojo = new SchoolMainInfoPojo();
                schoolMainInfoPojo.setSchoolNo(dispensingForm1.getSchoolNo());
                schoolMainInfoPojo.setInfoNo(dispensingForm1.getInfoNo());
                schoolMainInfoPojo.setInfoTitle(dispensingForm1.getInfoTitle());
                schoolMainInfoPojo.setInfoType(dispensingForm1.getInfoType());
                schoolMainInfoPojo.setInfoContent(dispensingForm1.getInfoContent());
                schoolMainInfoPojo.setCreateTime(dispensingForm1.getCreateTime());
                schoolMainInfoPojo.setCreateDate(dispensingForm1.getCreateDate());
                schoolMainInfoPojo.setLastModifyDate(dispensingForm1.getLastModifyDate());
                schoolMainInfoPojo.setLastModifyTime(dispensingForm1.getLastModifyTime());
                newSchoolMainInfoPojo.add(schoolMainInfoPojo);
                schoolMainInfoPojoDispensing.add(schoolMainInfoPojo);
            }
                if (schoolMainInfoPojoDispensing.size()>0){
                    sc.add(schoolMainInfoPojoDispensing);
                }
        }

//        for (ScholarshipForm scholarshipForm1:scholarshipForm) {
//            newSchoolMainInfoPojo.add(scholarshipForm1);
//            schoolMainInfoPojos.add(scholarshipForm1);
//        }
//        sc.add(schoolMainInfoPojos);
//        schoolMainInfoPojos.clear();
        if (titleForm.size()>0){
                for (TitleForm titleForm1 : titleForm) {
                    if ("".equals(titleForm1.getInfoTitle()) || "".equals(titleForm1.getInfoContent())) {
                        continue;
                    }
                    schoolMainInfoPojo = new SchoolMainInfoPojo();
                    schoolMainInfoPojo.setSchoolNo(titleForm1.getSchoolNo());
                    schoolMainInfoPojo.setInfoNo(titleForm1.getInfoNo());
                    schoolMainInfoPojo.setInfoTitle(titleForm1.getInfoTitle());
                    schoolMainInfoPojo.setInfoType(titleForm1.getInfoType());
                    schoolMainInfoPojo.setInfoContent(titleForm1.getInfoContent());
                    schoolMainInfoPojo.setCreateTime(titleForm1.getCreateTime());
                    schoolMainInfoPojo.setCreateDate(titleForm1.getCreateDate());
                    schoolMainInfoPojo.setLastModifyDate(titleForm1.getLastModifyDate());
                    schoolMainInfoPojo.setLastModifyTime(titleForm1.getLastModifyTime());
                    newSchoolMainInfoPojo.add(schoolMainInfoPojo);
                    schoolMainInfoPojoTitle.add(schoolMainInfoPojo);
                }
            if (schoolMainInfoPojoTitle.size()>0){
                sc.add(schoolMainInfoPojoTitle);
            }
        }
        newSchoolDetailPojo.setSchoolMainInfoPojos(sc);
    }

    /**
     *     根据专业代码判断是专硕还是学硕
     */
    private String calZhuanOrXue(String majorCode){
        String target = "";
        char c = majorCode.charAt(2);
        target = c =='5'?"专业型硕士":"学术性硕士";
        return target;
    }

    /**
     * 设置专业pojo里的学科门类和一级学科信息
     * @param majorPojo
     * @param majorCode
     */
    public  void setFirstCourseAndSecondCourse(MajorPojo majorPojo,String majorCode){
        String firstCourseCode = "";
        String secondCourseCode = "";
        char[] chars = majorCode.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i<2){
                firstCourseCode += chars[i];
                secondCourseCode += chars[i];
            }else if(i>=2&&i<4){
                secondCourseCode += chars[i];
            }else{
                break;
            }
        }
        int firstCourseNo = firstCourseMapper.getByCode(firstCourseCode).getFirstCourseNo();
        int secondCourseNo = secondCourseMapper.getByCode(secondCourseCode).getSecondCourseNo();
        majorPojo.setFirstCourseNo(firstCourseNo);
        majorPojo.setSecondCourseNo(secondCourseNo);
    }

    private boolean dealDepartmentInfo(List<DepartmentPojo> oriDepartmentPojos, List<DepartmentPojo> newDepartmentPojos,String schoolNo) {
            if (newDepartmentPojos.size() == 0)
                return true;
            //先处理没有ID的数据
            Iterator<DepartmentPojo> newIteratorDepartmentPojos = newDepartmentPojos.iterator();
            while(newIteratorDepartmentPojos.hasNext()){
                    DepartmentPojo departmentPojo = newIteratorDepartmentPojos.next();
                if (departmentPojo.getDepartmentId()<=0){
                    String majorCode = departmentPojo.getMajorCode();
                    MajorPojo majorPojo = majorMapper.isExist(departmentPojo.getMajorCode(), departmentPojo.getMajorName());
                    int majorNo = 0;
                    if (majorPojo == null){
                        majorPojo = new MajorPojo();
                        majorPojo.setMajorName(departmentPojo.getMajorName());
                        majorPojo.setMajorCode(departmentPojo.getMajorCode());
                        majorPojo.setMajorType(calZhuanOrXue(departmentPojo.getMajorCode()));
                        majorPojo.setSchoolNo(schoolNo);
                        setFirstCourseAndSecondCourse(majorPojo,majorCode);
                        majorPojo.setCurrYear(dateUtil.getCurrDate());
                        boolean add = majorMapper.add(majorPojo);
                        majorNo = majorPojo.getMajorNo();
                        if (!add){
                            return false;
                        }
                    }
                    majorNo = majorPojo.getMajorNo();
                    departmentPojo.setMajorNo(majorNo);
                    SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo(schoolNo);
                    departmentPojo.setSchoolName(bySchoolNo.getSchoolName());
                    //直接插入数据库
                    boolean add = departmentMapper.add(departmentPojo);
                    if(!add)
                        return false;
                    //添加成功后从集合中去除该元素
                    newIteratorDepartmentPojos.remove();
                }
        }
            //先把元素中主键相同的判断其他内容是否相同，相同则直接从集合中去除，不相同则更新后去除
        Iterator<DepartmentPojo> oriIterator = oriDepartmentPojos.iterator();
        Iterator<DepartmentPojo> newIterator = newDepartmentPojos.iterator();
        while(oriIterator.hasNext()){
            DepartmentPojo oriDepartmentPojo = oriIterator.next();
            while(newIterator.hasNext()){
                DepartmentPojo newDepartmentPojo = newIterator.next();
                int oriDepartmentId = oriDepartmentPojo.getDepartmentId();
                int newDepartmentId = newDepartmentPojo.getDepartmentId();
                //相同元素
                if (oriDepartmentId == newDepartmentId){
                    if (oriDepartmentPojo.equals(newDepartmentPojo)){
                        oriIterator.remove();
                        newIterator.remove();
                        //跳出最里层for循环
                        break;
                    }
                    if (!newDepartmentPojo.getMajorCode().equals(oriDepartmentPojo.getMajorCode())||
                            !newDepartmentPojo.getDepartmentName().equals(oriDepartmentPojo.getDepartmentName())||
                            !newDepartmentPojo.getMajorName().equals(oriDepartmentPojo.getMajorName())){
                        //已有院系而新增的专业信息操作
                        String newMajorCode = newDepartmentPojo.getMajorCode();
                        int newMajorNo = majorMapper.getByMajorCode(newDepartmentPojo.getMajorCode()).get(0).getMajorNo();
                        //不存在这个专业(1.新增专业(新增一条数据)，2.修改后没有这个专业(修改这条数据))
                        if (newDepartmentPojo.getMajorNo() <= 0||newMajorNo <= 0){
                            MajorPojo majorPojo = majorMapper.isExist(newDepartmentPojo.getMajorCode(), newDepartmentPojo.getMajorName());
                            int majorNo = 0;
                            if (majorPojo == null){
                                majorPojo.setMajorName(newDepartmentPojo.getMajorName());
                                majorPojo.setMajorCode(newDepartmentPojo.getMajorCode());
                                majorPojo.setMajorType(calZhuanOrXue(newDepartmentPojo.getMajorCode()));
                                majorPojo.setSchoolNo(schoolNo);
                                setFirstCourseAndSecondCourse(majorPojo,newMajorCode);
                                majorPojo.setCurrYear(dateUtil.getCurrDate());
                                boolean add = majorMapper.add(majorPojo);
                                majorNo = majorPojo.getMajorNo();
                                if (!add){
                                    return false;
                                }
                            }
                            majorNo = majorPojo.getMajorNo();
                            newDepartmentPojo.setMajorNo(majorNo);
                        }else{
                            //已有院系而需修改已存在的专业信息操作
//                        String newMajorCode = newDepartmentPojo.getMajorCode();
                            MajorPojo byMajorNo = majorMapper.getByMajorNo(newMajorNo);
                            if (byMajorNo.getMajorCode().equals(newDepartmentPojo.getMajorCode())){
                                boolean update = departmentMapper.update(newDepartmentPojo);
                                if (update == false)
                                    return false;
                            }
                        }
                        oriIterator.remove();
                        newIterator.remove();
                        break;
                    }

                }
            }
        }
//        for (DepartmentPojo oriDepartmentPojo:oriDepartmentPojos) {
//                for (DepartmentPojo newDepartmentPojo:newDepartmentPojos) {
//                    int oriDepartmentId = oriDepartmentPojo.getDepartmentId();
//                    int newDepartmentId = newDepartmentPojo.getDepartmentId();
//                    //相同元素
//                    if (oriDepartmentId == newDepartmentId){
//                        if (oriDepartmentPojo.equals(newDepartmentPojo)){
//                            oriDepartmentPojos.remove(oriDepartmentPojo);
//                            newDepartmentPojos.remove(newDepartmentPojo);
//                            //跳出最里层for循环
//                            break;
//                        }
//                        if (!newDepartmentPojo.getMajorCode().equals(oriDepartmentPojo.getMajorCode())||
//                                !newDepartmentPojo.getDepartmentName().equals(oriDepartmentPojo.getDepartmentName())||
//                                newDepartmentPojo.getMajorName().equals(oriDepartmentPojo.getMajorName())){
//                            int newMajorNo = majorMapper.getByMajorCode(newDepartmentPojo.getMajorCode()).get(0).getMajorNo();
//                            if (oriDepartmentPojo.getMajorNo()!=newMajorNo){
//                                newDepartmentPojo.setMajorNo(newMajorNo);
//                            }
//                            boolean update = departmentMapper.update(newDepartmentPojo);
//                            if (update == false)
//                                return false;
//                            oriDepartmentPojos.remove(oriDepartmentPojo);
//                            newDepartmentPojos.remove(newDepartmentPojo);
//                            break;
//                        }
//
//                    }
//                }
//            }


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

    private boolean dealSchoolMainInfo(List<SchoolMainInfoPojo> oriSchoolDetailPojoSchoolMainInfoPojos, List<SchoolMainInfoPojo> newSchoolDetailPojoSchoolMainInfoPojos, String schoolNo) {
        if (oriSchoolDetailPojoSchoolMainInfoPojos.size() == 0) {
            //若数据库中没有此学校的明细信息，则直接插入数据
            for (SchoolMainInfoPojo schoolMainInfoPojo:newSchoolDetailPojoSchoolMainInfoPojos) {
                if (!"".equals(schoolMainInfoPojo.getInfoTitle())||!"".equals(schoolMainInfoPojo.getInfoContent())){
                    setSchoolMainInfoDateTime(schoolMainInfoPojo);
                    schoolMainInfoPojo.setSchoolNo(schoolNo);
                    boolean flag = schoolMainInfoMapper.add(schoolMainInfoPojo);
                    if (flag == false)
                        return false;
                }
            }
        }else{


            //若数据库中存在该学校的明细信息，则遍历处理信息
            //获取分类好的信息map集合
            Map<String, List<SchoolMainInfoPojo>> oriSchoolMainInfoMaps = getSchoolMainInfoMaps(oriSchoolDetailPojoSchoolMainInfoPojos);
            LinkedHashMap<String, List<SchoolMainInfoPojo>> newSchoolMainInfoMaps = getSchoolMainInfoMaps(newSchoolDetailPojoSchoolMainInfoPojos);
//            newSchoolMainInfoMaps.remove()
            //先处理数据库中该信息类型不存在的数据
            for (Map.Entry<String, List<SchoolMainInfoPojo>> newEntry : newSchoolMainInfoMaps.entrySet()){
                String newInfoType = newEntry.getKey();
                List<SchoolMainInfoPojo> value = newEntry.getValue();
                List<SchoolMainInfoPojo> byInfoType = schoolMainInfoMapper.getByInfoType(newInfoType, schoolNo);
                if (byInfoType.size()==0){
                    //直接插入数据
                    for (SchoolMainInfoPojo sc : value){
                        setSchoolMainInfoDateTime(sc);
                        boolean update = schoolMainInfoMapper.add(sc);
                        if (update == false)
                            return false;
                    }
                }
            }
            for (Map.Entry<String, List<SchoolMainInfoPojo>> oriEntry : oriSchoolMainInfoMaps.entrySet()) {
                for (Map.Entry<String, List<SchoolMainInfoPojo>> newEntry : newSchoolMainInfoMaps.entrySet()) {
                    //类型存在且两者信息类型相同的map集合
                    if (oriEntry.getKey().equals(newEntry.getKey())){
                        List<SchoolMainInfoPojo> oriEntryValue = oriEntry.getValue();
                        List<SchoolMainInfoPojo> newEntryValue = newEntry.getValue();
                        //1.先将信息号为空数据直接插入数据库，并且在map中移除该数据
                        Iterator<SchoolMainInfoPojo> iterator = newEntry.getValue().iterator();
                        while (iterator.hasNext()){
                            SchoolMainInfoPojo sc = iterator.next();
                            boolean flag = false;
                            if (sc.getInfoNo()== 0){
                                setSchoolMainInfoDateTime(sc);
                                boolean add = schoolMainInfoMapper.add(sc);
                                if (add == false)
                                    return false;
                                flag = true;
//                                newEntry.getValue().remove(sc);
//                                newSchoolMainInfoMaps.remove(newEntry.getKey(),sc);
                            }
                            if (flag)
                                iterator.remove();
//                                newEntryValue.remove(sc);
                        }
                        //2.再将剩下的数据进行处理,剩下的数据都是有信息号的,根据信息号进行处理
                        //两个信息集合数量相等则更新信息集合
                        if (oriEntryValue.size() == newEntryValue.size()){
                            dealMatchSchoolMainInfo(oriEntryValue,newEntryValue);
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
        Iterator<SchoolMainInfoPojo> iterator = oriEntryValue.iterator();
        Iterator<SchoolMainInfoPojo> iterator1 = newEntryValue.iterator();
        while(iterator.hasNext()){
            SchoolMainInfoPojo orischoolMainInfoPojo = iterator.next();
            while(iterator1.hasNext()){
                SchoolMainInfoPojo newschoolMainInfoPojo = iterator1.next();
                if (orischoolMainInfoPojo.getInfoNo() == newschoolMainInfoPojo.getInfoNo()){
                    if (!orischoolMainInfoPojo.getInfoTitle().equals(newschoolMainInfoPojo.getInfoTitle())
                            ||!orischoolMainInfoPojo.getInfoContent().equals(newschoolMainInfoPojo.getInfoContent())){
                        if ("".equals(newschoolMainInfoPojo.getCreateDate())||"".equals(newschoolMainInfoPojo.getCreateTime())){
                            setSchoolMainInfoDateTime(newschoolMainInfoPojo);
                        }else {
                            setSchoolMainInfoModifyDateTime(newschoolMainInfoPojo);
                        }
                        boolean update = schoolMainInfoMapper.update(newschoolMainInfoPojo);
                        if (update == false)
                            return false;
                        iterator.remove();
                        iterator1.remove();
                    }
//                    oriEntryValue.remove(orischoolMainInfoPojo);
//                    newEntryValue.remove(newschoolMainInfoPojo);
//                    return true;

                }
            }
        }

            //ori>new / ori<new
            //全部遍历后，剩下的ori都是需要删除的，然后批量操作这些数据
            for (SchoolMainInfoPojo orischoolMainInfoPojo:newEntryValue) {
                boolean update = schoolMainInfoMapper.delete(String.valueOf(orischoolMainInfoPojo.getInfoNo()));
                if (update == false)
                    return false;
            }

            //全部遍历后，剩下的new都是需要添加的,然后批量操作这些数据
            for (SchoolMainInfoPojo newschoolMainInfoPojo:newEntryValue) {
                boolean update = schoolMainInfoMapper.add(newschoolMainInfoPojo);
                if (update == false)
                    return false;
            }
        return true;
    }

    private void setSchoolMainInfoModifyDateTime(SchoolMainInfoPojo newschoolMainInfoPojo) {
        newschoolMainInfoPojo.setLastModifyDate(dateUtil.getCurrDate(DateUtil.formatPattern2));
        newschoolMainInfoPojo.setLastModifyTime(dateUtil.getCurrDate(DateUtil.formatPattern3));
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

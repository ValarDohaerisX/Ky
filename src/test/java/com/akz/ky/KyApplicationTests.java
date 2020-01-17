package com.akz.ky;

import com.akz.ky.controller.SchoolController;
import com.akz.ky.mapper.ManagerMapper;
import com.akz.ky.mapper.SchoolMainInfoMapper;
import com.akz.ky.mapper.SchoolMapper;
import com.akz.ky.pojo.LdcodePojo;
import com.akz.ky.pojo.ManagerPojo;
import com.akz.ky.pojo.SchoolMainInfoPojo;
import com.akz.ky.pojo.SchoolPojo;
import com.akz.ky.service.LdcodeService;
import com.akz.ky.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@SpringBootTest
@RunWith(SpringRunner.class)
class KyApplicationTests {

    @Autowired(required = false)
    ManagerMapper managerMapper;

    @Autowired(required = false)
    LdcodeService ldcodeService;
    @Autowired(required = false)
    SchoolMapper schoolMapper;
    @Autowired
    SchoolController schoolController;
    @Autowired(required = false)
    SchoolMainInfoMapper schoolMainInfoMapper;
    @Autowired
    SchoolService schoolService;
    @Test
    public void test4(){
        List<SchoolMainInfoPojo> byAll = schoolMainInfoMapper.getByAll();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = schoolService.getSchoolMainInfoMaps(byAll);
//        System.out.println(schoolMainInfoMaps);
        for (Map.Entry<String, List<SchoolMainInfoPojo>> entry:schoolMainInfoMaps.entrySet()) {
            System.out.println("信息类型["+entry.getKey()+"]:\n信息集合"+entry.getValue());
        }
    }
    @Test
    public void test3(){
        SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo("4");
        String schoolLevel = bySchoolNo.getSchoolLevel();
        System.out.println(bySchoolNo.getSchoolLevel());
        //        ["一流学科","985高校","211高校"]
        char[] chars = schoolLevel.toCharArray();
        String s = "";
        for (char c:chars){
            s+=(('['!=c&&'\"'!=c&&']'!=c)?((c==','?'|':c)):"");
        }
        System.out.println("s------->"+s);
    }
    @Test
    public void test2(){
        List<LdcodePojo> schoolType = ldcodeService.getLdCodeByCodeType("schoolType");
        ldcodeService.getLdCodeBySchoolType();
        ldcodeService.getLdCodeBySchoolLevel();
        ldcodeService.getLdCodeByAddress();
        for (LdcodePojo ldcode:schoolType) {
            System.out.println("codeType:"+ldcode.getCodeType()+",codeName:"+ldcode.getCodeName());
        }
    }
    @Test
    public void test1(){
        List<ManagerPojo> all = managerMapper.allManaList();
        System.out.println(all.toString());
    }

}

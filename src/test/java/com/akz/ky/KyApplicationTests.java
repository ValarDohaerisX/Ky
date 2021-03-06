package com.akz.ky;

import com.akz.ky.controller.SchoolController;
import com.akz.ky.mapper.ManagerMapper;
import com.akz.ky.mapper.SchoolMainInfoMapper;
import com.akz.ky.mapper.SchoolMapper;
import com.akz.ky.pojo.*;
import com.akz.ky.service.LdcodeService;
import com.akz.ky.service.SchoolService;
import com.akz.ky.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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
    @Autowired
    UserService userService;

    @Test
    public void test6(){
        List<UserPojo> userPojos = userService.list();
        System.out.println("user:"+userPojos);
    }
    @Test
    public void test5(){
        List<Integer> i = new ArrayList<>();
        i.add(1);i.add(2);i.add(3);i.add(4);i.add(5);
        List<Integer> j = new ArrayList<>();
        j.add(1);j.add(2);j.add(3);
        Iterator<Integer> ii = i.iterator();
        Iterator<Integer> jj = j.iterator();
        while (ii.hasNext()){
            Integer next = ii.next();
            System.out.println("i:"+next);
            while(jj.hasNext()){
                Integer next1 = jj.next();
                System.out.println("j:"+next1);
                if (next1==2){
                    System.out.println("执行到中断代码");
                    jj.remove();
                    break;
                }
            }
        }

    }
    @Test
    public void test4(){
        List<SchoolMainInfoPojo> byAll = schoolMainInfoMapper.getByAll();
        LinkedHashMap<String, List<SchoolMainInfoPojo>> schoolMainInfoMaps = schoolService.getSchoolMainInfoMaps(byAll);
//        System.out.println(schoolMainInfoMaps);
        for (Map.Entry<String, List<SchoolMainInfoPojo>> entry:schoolMainInfoMaps.entrySet()) {
            System.out.println("��Ϣ����["+entry.getKey()+"]:\n��Ϣ����"+entry.getValue());
        }
    }
    @Test
    public void test3(){
        SchoolPojo bySchoolNo = schoolMapper.getBySchoolNo("4");
        String schoolLevel = bySchoolNo.getSchoolLevel();
        System.out.println(bySchoolNo.getSchoolLevel());
        //        ["һ��ѧ��","985��У","211��У"]
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

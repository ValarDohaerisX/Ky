package com.akz.ky;

import com.akz.ky.mapper.ManagerMapper;
import com.akz.ky.pojo.LdcodePojo;
import com.akz.ky.pojo.ManagerPojo;
import com.akz.ky.service.LdcodeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class KyApplicationTests {

    @Autowired(required = false)
    ManagerMapper managerMapper;

    @Autowired(required = false)
    LdcodeService ldcodeService;
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

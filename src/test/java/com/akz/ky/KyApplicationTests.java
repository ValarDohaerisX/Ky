package com.akz.ky;

import com.akz.ky.mapper.ManagerMapper;
import com.akz.ky.pojo.ManagerPojo;
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
    @Test
    public void test1(){
        List<ManagerPojo> all = managerMapper.allManaList();
        System.out.println(all.toString());
    }

}

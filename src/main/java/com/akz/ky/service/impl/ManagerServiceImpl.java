package com.akz.ky.service.impl;

import com.akz.ky.mapper.ManagerMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.ManagerPojo;
import com.akz.ky.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@CacheConfig(cacheNames = "managers")
public class ManagerServiceImpl implements ManagerService {
    @Autowired(required = false)
    ManagerMapper managerMapper;

    public Result login(ManagerPojo managerPojo, HttpSession session){
        if (managerPojo!=null){
                boolean exist = isExist(managerPojo.getName());
                if (exist){
                    ManagerPojo managerPojo1 = managerMapper.checkManager(managerPojo.getName(), managerPojo.getPassword());
                    System.out.println("managerPojo1--->"+managerPojo1);
                    if (managerPojo1!=null){
                        session.setAttribute("manager",managerPojo1);
                        return Result.success(managerPojo1);
                    }else {
                        return Result.failure(ApiReturnCode.C_Fail_Get,"errorPwd");
                    }
                }else {
                    return Result.failure(ApiReturnCode.C_Fail_Get,"noUser");
                }
        }else {
            return Result.failure(ApiReturnCode.C_Fail_Get,"noData");
        }
    }
    @Override
    public boolean isExist(String name) {
        ManagerPojo pojo = managerMapper.isExist(name);
        if (pojo == null)
            return false;
        else
            return true;
    }

    @Override
    public Result register(ManagerPojo managerPojo) {
        String name = managerPojo.getName();
        System.out.println("impl-name1-->"+name);
        String password = managerPojo.getPassword();
        name = HtmlUtils.htmlEscape(name);
        System.out.println("impl-name2-->"+name);
        boolean flag = isExist(name);
        if (flag){
            return Result.failure(ApiReturnCode.C_Fail_User_Repeat,"repeat");
        }
        managerPojo.setName(name);
        managerPojo.setPassword(password);
        managerPojo.setManaPemission(1);
        managerPojo.setManaType("一级管理员");
        managerPojo.setAppFlag(0);
        boolean f = managerMapper.registerOne(managerPojo);
        return f==true? Result.success("注册成功"): Result.failure(ApiReturnCode.C_Fail_Insert,"error");
    }

    @Override
    public Result confirmReg(int id) {
        ManagerPojo pojo = managerMapper.getById(id);
        if (pojo == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询结果为空");
        }
        if (pojo.getAppFlag() == 1){
            return Result.failure(ApiReturnCode.C_Fail_Update,"该用户已被批准，无需重复确认");
        }
        pojo.setAppFlag(1);
        managerMapper.update(pojo);
        return Result.success(ApiReturnCode.SUCCESS);
    }

    @Override
    @Cacheable(key="'managers-confirmToReg'")
    public Result<List<ManagerPojo>> confirmToRegList() {
        List<ManagerPojo> managerPojos = managerMapper.confirmToRegList();
        if (managerPojos == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询数据为空");
        }
        return Result.success(managerPojos);
    }

    @Override
    @Cacheable(key="'managers-all'")
    public Result<List<ManagerPojo>> allManaList() {
        List<ManagerPojo> managerPojos = managerMapper.allManaList();
        if (managerPojos == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询数据为空");
        }
        return Result.success(managerPojos);
    }

    @Override
    @Cacheable(key="'managers-one-'+ #p0")
    public Result<ManagerPojo> editManaInfo(int id) {
        ManagerPojo managerPojo = managerMapper.getById(id);
        if (managerPojo == null)
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询结果为空");
        return Result.success(managerPojo);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Result updateManaInfo(ManagerPojo managerPojo) {
        System.out.println("进入到mana-update..");
        System.out.println("pojo-->"+managerPojo);
        Integer pemission = managerPojo.getManaPemission();
        if (pemission == 1){
            managerPojo.setManaType("一级管理员");
        }else if (pemission == 2){
            managerPojo.setManaType("二级管理员");
        }else {
            managerPojo.setManaType("三级管理员");
        }
        boolean flag = managerMapper.update(managerPojo);
        if (flag){
            ManagerPojo pojo = managerMapper.getById(managerPojo.getId());
            return Result.success(pojo);
        }else {
            return Result.failure(ApiReturnCode.C_Fail_Update,"更新失败,请重试~");
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteManaInfo(int id) {
        System.out.println("进入到manager-serviceimpl中..");
//        boolean flag = managerMapper.deleteManaInfo(id);
//        if (flag)
//            Res
        return  managerMapper.deleteManaInfo(id);
    }

    @Override
    public Result<List<ManagerPojo>> getManaByNameLike(String name) {
        List<ManagerPojo> manaByNameLike = managerMapper.getManaByNameLike(name);
        if (manaByNameLike == null){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询: "+name+" 为空,请重新输入");
        }
        if (manaByNameLike.size() == 0){
            return Result.failure(ApiReturnCode.C_Fail_Get,"查询: "+name+" 为空,请重新输入");
        }
        return Result.success(manaByNameLike);
    }

    @Override
    public Result exit(HttpSession session, ManagerPojo managerPojo) {
        session.removeAttribute(managerPojo.getName());
        return Result.success(ApiReturnCode.SUCCESS);
    }
}

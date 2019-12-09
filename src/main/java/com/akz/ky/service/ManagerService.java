package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.ManagerPojo;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface ManagerService {

    Result<ManagerPojo> login(ManagerPojo managerPojo, HttpSession session);

    boolean isExist(String name);

    Result register(ManagerPojo managerPojo);

    Result confirmReg(int id);

    Result<List<ManagerPojo>> confirmToRegList();

    Result<List<ManagerPojo>> allManaList();

    Result<ManagerPojo> editManaInfo(int id);

    Result updateManaInfo(ManagerPojo managerPojo);

    boolean deleteManaInfo(int id);

    Result<List<ManagerPojo>> getManaByNameLike(String name);

    Result exit(HttpSession session, ManagerPojo managerPojo);
}

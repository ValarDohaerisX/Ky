package com.akz.ky.controller;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.ManagerPojo;
import com.akz.ky.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author lzx
 * @version 1.0
 * @date 2019/9/27 10:08
 */

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

//
//    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody ManagerPojo managerPojo, HttpSession session){
        System.out.println("ManagerController-login-mana-->"+managerPojo);
        return managerService.login(managerPojo,session);
    }

    @RequestMapping(value = "/exit",method = RequestMethod.POST)
    public Result exit(@RequestBody ManagerPojo managerPojo, HttpSession session){
        return managerService.exit(session,managerPojo);
    }
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result register(@RequestBody ManagerPojo managerPojo){
        return managerService.register(managerPojo);
    }

    @RequestMapping(value = "confirmReg",method = RequestMethod.POST)
    public Result confirmReg(@RequestBody int id){
        return managerService.confirmReg(id);
    }

    @RequestMapping(value = "confirmToRegList",method = RequestMethod.GET)
    public Result<List<ManagerPojo>> confirmToRegList(){
        return managerService.confirmToRegList();
    }

    @RequestMapping(value = "allManaList",method = RequestMethod.GET)
    public Result<List<ManagerPojo>> allManaList(){
        return managerService.allManaList();
    }

    @RequestMapping(value = "editManaInfo",method = RequestMethod.POST)
    public Result<ManagerPojo> editManaInfo(@RequestParam int id){
        return managerService.editManaInfo(id);
    }

    @RequestMapping(value = "updateManaInfo",method = RequestMethod.POST)
    public Result updateManaInfo(@RequestBody ManagerPojo managerPojo){
        return managerService.updateManaInfo(managerPojo);
    }

    @RequestMapping(value = "/deleteManaInfo",method = RequestMethod.DELETE)
    public boolean deleteManaInfo(@RequestParam int id){
        return managerService.deleteManaInfo(id);
    }

    @RequestMapping(value = "getManaByNameLike",method = RequestMethod.GET)
    Result<List<ManagerPojo>> getManaByNameLike(@RequestParam String name){
        return managerService.getManaByNameLike(name);
    }
    @RequestMapping(value = "getManaInfo",method = RequestMethod.GET)
    Result<List<ManagerPojo>> getManaInfo(@RequestParam String name){
        return managerService.getManaByNameLike(name);
    }
}

package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author AKZ
 * @despriction 用户信息表
 * @date 2019-09-05
 *
 */
@Data
public class UserPojo {
//    --id,name,password,realName,sex,mobile,schoolname,grade,major,
// userType,userPemission,signature,activity,badge,regTime
    //主键
    int id;
    //用户名
    String name;
    //用户密码
    String password;
    //用户真实姓名
    String realName;
    //用户性别
    char sex;
    //用户联系方式
    String mobile;
    //学校名称
    String schoolName;
    //年级
    int grade;
    //专业
    String major;
    //目标院校
    String aimSchool;
    //目标专业
    String aimMajor;
    //用户类型
    String userType;
    //用户所属权限
    int userPemission;
    //个性签名
    String signature;
    //活跃度
    int activity;
    //所属徽章
    String badge;
    //注册时间
    Timestamp regTime;
    //上一次登录时间
    Timestamp lastLoginTime;
}


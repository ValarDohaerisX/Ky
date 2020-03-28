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
    int userNo;
    //账号
    String userLoginName;
    //用户密码
    String userLoginPassword;
    //用户名
    String userName;
    //用户性别
    char userSex;
    //用户所在城市
    String userCity;
    //用户联系方式
    String userContact;
    //个性签名
    String userSignature;
    //用户所在院校
    String userSchoolName;
    //用户所属专业
    String userMajorName;
    //用户目标院校
    String userTagetSchoolName;
    //用户目标专业
    String userTagetMajorlName;
    //用户累计积分
    int userIntegral;
    //用户等级
    int userLevel;
    //上一次登录时间
    Timestamp lastLoginDate;
    //用户注册日期
    Timestamp createDate;
    //用户注册时间
    Timestamp createTime;
    //信息修改日期
    Timestamp modifyDate;
    //信息修改时间
    Timestamp modifyTime;

    //目标专业
//    String aimMajor;
    //用户类型
//    String userType;
    //用户所属权限
//    int userPemission;

    //活跃度
//    int activity;
    //所属徽章
//    String badge;
    //注册时间
//    Timestamp regTime;
    //上一次登录时间
//    Timestamp lastLoginTime;
}


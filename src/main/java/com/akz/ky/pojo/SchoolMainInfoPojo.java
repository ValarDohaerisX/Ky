package com.akz.ky.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/21 16:29
 * @Description 目标院校信息主表
 */
@Data
public class SchoolMainInfoPojo {
    //信息号
    int infoNo;
    //信息类型
    String infoType;
    //信息标题
    String infoTitle;
    //信息内容
    String infoContent;
    //目标院校编号
    String schoolNo;
    //创建日期
    String createDate;
    //创建时间
    String createTime;
    //上一次修改日期
    String lastModifyDate;
    //上一次修改时间
    String lastModifyTime;
}

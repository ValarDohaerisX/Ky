package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/6 11:45
 * @Description 视频库
 */
@Data
public class VideoPojo {

    //视频号
    int videoNo;
    //标题
    String videoTitle;
    //视频类型
    String videoType;
    //视频子类型
    String videoType2;
    //视频地址
    String videoAddress;
    //上载作者号
    String videoUploadAuthorNo;
    //上载作者名字
    String videoUploadAuthorName;
    //视频点击量
    int videoClickNum;
    //视频点踩量
    int videoBadNum;
    //视频审核标志(1 已通过 0 未通过)
    String videoVerifyState;
    //上传日期
    Date createDate;
    //上传时间
    Date createTime;
    //修改日期
    Date modifyDate;
    //修改时间
    Date modifyTime;

}

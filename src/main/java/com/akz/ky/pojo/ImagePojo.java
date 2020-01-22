package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/6 11:28
 * @Description
 */
@Data
public class ImagePojo {
    //图片号
    int imageNo;
    /**
     * imageType 视频类型
     * imageType:user 头像
     * imageType:logo 校徽
     * imageType:Qrcode_detail 手机网站
     * imageType:Qrcode_public 掌上研招
     * imageType:size 图片尺寸
     *  imageType2:max 大
     *  imageType2:middle 中
     *  imageType2:min 小
     * imageType:emoj 表情包
     *
     */
    String imageType;
    //视频子类型
    String imageType2;
    //图片地址
    String imageAddress;
    //所属号
    String numberNo;
    //所属号类型（用户？视频？校徽？）
    String numberType;
    //上传日期
    Date createDate;
    //上传时间
    Date createTime;
    //修改日期
    Date modifyDate;
    //修改时间
    Date modifyTime;
    //是否使用标志(1 正在使用 0 已停用)
    String useFlag;

}

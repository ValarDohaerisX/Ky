package com.akz.ky.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author AKZ
 * @despriction 回帖表
 * @date 2019-09-05
 *
 */

@Data
public class ChildTiePojo {

    //主键
    int id;

    //回帖内容
    String content;

    //点赞数量
    int goodNum;

    //回帖时间
    Date createdTime;

    int reviewNum;

    //用户id
    int userId;

    //主贴id
    int mainTieId;

    //回复层主id
    int childTieId;

}

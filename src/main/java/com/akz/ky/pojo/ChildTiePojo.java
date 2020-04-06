package com.akz.ky.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author AKZ
 * @despriction 回帖表
 * @date 2019-09-05
 *
 */

@Data
public class ChildTiePojo {

    //主键
    int childTieNo;

    //回帖内容
    String comment;

    //用户id
    int userNo;
    //用户id
    String userName;
    //点赞数量
    int good;
    //差评数量
    int bad;
    //回帖时间
    String createdTime;
    //主贴id
    int mainTieNo;

    //回复层主id
    int childTieNoo;

}

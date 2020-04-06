package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author AKZ
 * @despriction 发帖表
 * @date 2019-09-05
 *
 */

@Data
public class MainTiePojo {

    //主键
    int mainTieNo;

    //标题
    String title;

    //内容
    String content;

    //用户id
    int userNo;

    //用户名
    String userName;

    //评论数量
    int reviewNum;

    //点赞数量
    int goodNum;

    //踩数量
    int badNum;

    //帖子创建时间
    String createdTime;

    //最后一次回帖时间
    String lastReplyTime;

}

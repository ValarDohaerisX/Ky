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
    int id;

    //标题
    String Title;

    //内容
    String content;

    //用户id
    int userId;

    //评论数量
    int reviewNum;

    //点赞数量
    int GoodNum;

    //踩数量
    int BadNum;

    //帖子创建时间
    Timestamp createdTime;

    //最后一次回帖时间
    Timestamp lastReplyTime;

}

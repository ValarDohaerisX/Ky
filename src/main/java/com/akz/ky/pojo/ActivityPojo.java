package com.akz.ky.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AKZ
 * @despriction 徽章表，用户的不同活跃度会有相对于的徽章显示
 * @date 2019-09-05
 *
 */
@Data
public class ActivityPojo {

    //主键
    int id;

    //徽章名称
    String name;

    //徽章等级
    int grade;

    //活跃度
    int level;

    //创建时间
    Timestamp createTime;

    //徽章集
    public static final List<ActivityPojo> activity = new ArrayList<>();


}

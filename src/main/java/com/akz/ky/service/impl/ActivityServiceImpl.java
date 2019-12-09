package com.akz.ky.service.impl;

import com.akz.ky.mapper.ActivityMapper;
import com.akz.ky.pojo.ActivityPojo;
import com.akz.ky.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ActivityServiceImpl implements ActivityService{
    @Autowired
    ActivityMapper activityMapper;
    public static final List<ActivityPojo> list = new ArrayList<>();
    @Override
    public void init() {
        List<ActivityPojo> pojos = activityMapper.list();
        list.addAll(pojos);
    }

    @Override
    public List<ActivityPojo> list() {
        return activityMapper.list();
    }

}

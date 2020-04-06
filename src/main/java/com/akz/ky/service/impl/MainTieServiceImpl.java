package com.akz.ky.service.impl;

import com.akz.ky.mapper.MainTieMapper;
import com.akz.ky.pojo.MainTiePojo;
import com.akz.ky.service.MainTieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/30 21:28
 * @Description
 */
@Service
@CacheConfig(cacheNames = "mainTies")
public class MainTieServiceImpl  implements MainTieService {
    @Autowired(required = false)
    MainTieMapper mainTieMapper;
    @Override
    @CacheEvict(allEntries = true)
    public boolean add(MainTiePojo mainTiePojo) {
        return mainTieMapper.add(mainTiePojo);
    }

    @Override
    @Cacheable(key="'mainTies-all'")
    public List<MainTiePojo> getMainTie() {
        return mainTieMapper.getMainTie();
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean update(MainTiePojo mainTiePojo) {
        return mainTieMapper.update(mainTiePojo);
    }

    @Override
    @Cacheable(key="'mainTies-one-'+ #p0")
    public MainTiePojo getOne(int mainTieNo) {
        return mainTieMapper.getOne(mainTieNo);
    }
}

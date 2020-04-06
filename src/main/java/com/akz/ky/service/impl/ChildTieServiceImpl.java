package com.akz.ky.service.impl;

import com.akz.ky.mapper.ChildTieMapper;
import com.akz.ky.pojo.ChildTiePojo;
import com.akz.ky.service.ChildTieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020.4.1 20:08
 * @Description
 */
@Service
@CacheConfig(cacheNames = "chileTie")
public class ChildTieServiceImpl implements ChildTieService {

    @Autowired(required = false)
    ChildTieMapper childTieMapper;
    @Override
    @CacheEvict(allEntries = true)
    public boolean add(ChildTiePojo childTiePojo) {
        return childTieMapper.add(childTiePojo);
    }

    @Override
    @Cacheable(key="'chileTie-one-childTieNo-'+ #p0")
    public ChildTiePojo get(int childTieNo) {
        return childTieMapper.get(childTieNo);
    }

    @Override
    @Cacheable(key="'chileTie-one-mainTieNo-'+ #p0")
    public List<ChildTiePojo> getChidByMainTieNo(int mainTieNo) {
        return childTieMapper.getChidByMainTieNo(mainTieNo);
    }

    @Override
    @Cacheable(key="'chileTie-all'")
    public List<ChildTiePojo> getAll() {
        return childTieMapper.getAll();
    }
}

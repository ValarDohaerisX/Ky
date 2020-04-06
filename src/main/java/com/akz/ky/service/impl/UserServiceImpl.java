package com.akz.ky.service.impl;

import com.akz.ky.mapper.UserMapper;
import com.akz.ky.pojo.UserPojo;
import com.akz.ky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;
    @Override
    @CacheEvict(allEntries = true)
    public boolean add(UserPojo userPojo) {
        return userMapper.add(userPojo);
    }

    @Override
    @Cacheable(key="'users-one-byId-'+ #p0")
    public UserPojo get(int uid) {
        return userMapper.getById(uid);
    }

    @Override
    @Cacheable(key="'users-one-byName-'+ #p0")
    public UserPojo get(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public UserPojo checkUser(String name, String password) {
        return userMapper.checkUser(name, password);
    }

    @Override
    public boolean isExists(String name) {
        return userMapper.isExists(name)==null?false:true;
    }

    @Override
    @Cacheable(key = "'users-all'")
    public List<UserPojo> list() {
        return userMapper.list();
    }

    @Override
    public List<UserPojo> list(UserPojo userPojo) {
        return userMapper.listByUser(userPojo);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean update(UserPojo userPojo) {
        return userMapper.update(userPojo);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean delete(int uid) {
        return userMapper.delete(uid);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean updateLoginTime(UserPojo userPojo) {
        return userMapper.updateLoginTime(userPojo);
    }
}

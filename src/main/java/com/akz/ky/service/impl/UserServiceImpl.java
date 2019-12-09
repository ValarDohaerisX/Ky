package com.akz.ky.service.impl;

import com.akz.ky.mapper.UserMapper;
import com.akz.ky.pojo.UserPojo;
import com.akz.ky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;
    @Override
    public boolean add(UserPojo userPojo) {
        return userMapper.add(userPojo);
    }

    @Override
    public UserPojo get(int uid) {
        return userMapper.getById(uid);
    }

    @Override
    public UserPojo get(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public UserPojo checkUser(String name, String password) {
        return userMapper.checkUser(name, password);
    }

    @Override
    public List<UserPojo> list() {
        return userMapper.list();
    }

    @Override
    public List<UserPojo> list(UserPojo userPojo) {
        return userMapper.listByUser(userPojo);
    }

    @Override
    public boolean update(UserPojo userPojo) {
        return userMapper.update(userPojo);
    }

    @Override
    public boolean delete(int uid) {
        return userMapper.delete(uid);
    }
}

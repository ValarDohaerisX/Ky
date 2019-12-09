package com.akz.ky.service;

import com.akz.ky.pojo.UserPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    boolean add(UserPojo userPojo);

    UserPojo get(int uid);

    UserPojo get(String name);

    UserPojo checkUser(String name, String password);

    List<UserPojo> list();

    List<UserPojo> list(UserPojo userPojo);

    boolean update(UserPojo userPojo);

    boolean delete(int uid);
}

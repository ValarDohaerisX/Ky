package com.akz.ky.service;

import com.akz.ky.pojo.MainTiePojo;
import sun.applet.Main;

import java.util.List;

public interface MainTieService {
    boolean add(MainTiePojo mainTiePojo);

    List<MainTiePojo> getMainTie();

    boolean update(MainTiePojo mainTiePojo);

    MainTiePojo getOne(int mainTieNo);

}

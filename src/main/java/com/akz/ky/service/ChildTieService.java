package com.akz.ky.service;

import com.akz.ky.pojo.ChildTiePojo;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020.4.1 20:07
 * @Description
 */
public interface ChildTieService {

    boolean add(ChildTiePojo childTiePojo);

    ChildTiePojo get(int childTieNo);

    List<ChildTiePojo> getChidByMainTieNo(int mainTieNo);

    List<ChildTiePojo> getAll();
}

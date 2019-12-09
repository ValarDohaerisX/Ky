package com.akz.ky.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AKZ
 * @despriction 管理员表
 * @date 2019-09-05
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerPojo {

    //主键
    Integer id;

    //管理员名称
    String name;

    //管理员密码
    String password;

    //管理员类型
    String manaType;

    //管理员所属权限
    Integer manaPemission;

    int appFlag;

}

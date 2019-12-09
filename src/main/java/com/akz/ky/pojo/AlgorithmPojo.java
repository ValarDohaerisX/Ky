package com.akz.ky.pojo;

import lombok.Data;

/**
 * @author AKZ
 * @despriction 算法表
 * @date 2019-09-05
 *
 */

@Data
public class AlgorithmPojo {
    //主键
    int id;
    //算法结构
    String param;
    //算法名称
    String paramName;
    //算法类型
    String type;
}

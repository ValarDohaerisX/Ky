package com.akz.ky.pojo;

import java.util.Date;

/**
 * @author AKZ
 * @despriction 用户头像表
 * @date 2019-09-05
 *
 */

public class UserImagePojo {

    //主键
    int id;

    //用户主键
    int userId;

    //图片类型
    String type;

    //图片位置
    String address;

    //图片创建时间
    Date createTime;

    //图片修改时间
    Date modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "UserImagePojo{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}

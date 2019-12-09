package com.akz.ky.pojo;

/**
 * @author AKZ
 * @despriction 学校图片表
 * @date 2019-09-05
 *
 */
public class SchoolImagePojo {

    //主键
    int id;

    //学校id
    int schoolId;

    //图片类型
    String type;

    //图片位置
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
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

    @Override
    public String toString() {
        return "SchoolImagePojo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

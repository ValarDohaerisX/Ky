package com.akz.ky.mapper;

import com.akz.ky.pojo.SchoolPojo;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/26 20:11
 * @Description
 */

@Mapper
public interface SchoolMapper {
    @Insert("<script>insert into school " +
            "set schoolName = #{s.schoolName}," +
            "schoolCode = #{s.schoolCode}," +
            "schoolType = #{s.schoolType}," +
            "schoolLevel = #{s.schoolLevel}," +
            "address = #{s.address}," +
            "schoolInfo = #{s.schoolInfo}," +
            "schoolMail = #{s.schoolMail}," +
            "schoolMobile = #{s.schoolMobile}," +
            "schoolAddress = #{s.schoolAddress}," +
            "schoolOnlineNet = #{s.schoolOnlineNet}," +
            "schoolKyStudentOnlineNet = #{s.schoolKyStudentOnlineNet} " +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean add(@Param("s") SchoolPojo schoolPojo);

    @Update({"<script>update school " +
            "<if test = 's.schoolName ! = null'>set schoolName = #{s.schoolName} , </if>" +
            "<if test = 's.schoolCode ! = null'>set schoolCode = #{s.schoolCode} , </if>" +
            "<if test = 's.schoolType ! = null'>set schoolType = #{s.schoolType} , </if>" +
            "<if test = 's.schoolLevel ! = null'>set schoolLevel = #{s.schoolLevel} , </if>" +
            "<if test = 's.address ! = null'>set address = #{s.address} , </if>" +
            "<if test = 's.schoolInfo ! = null'>set schoolInfo = #{s.schoolInfo} , </if>" +
            "<if test = 's.schoolMail ! = null'>set schoolMail = #{s.schoolMail} , </if>" +
            "<if test = 's.schoolMobile ! = null'>set schoolMobile = #{s.schoolMobile} , </if>" +
            "<if test = 's.schoolAddress ! = null'>set schoolAddress = #{s.schoolAddress} , </if>" +
            "<if test = 's.schoolOnlineNet ! = null'>set schoolOnlineNet = #{s.schoolOnlineNet} ,</if>" +
            "<if test = 's.schoolKyStudentOnlineNet ! = null'>set schoolKyStudentOnlineNet = #{s.schoolKyStudentOnlineNet} </if>" +
            " where schoolNo = #{s.schoolNo}" +
            "</script>"})
    boolean update(@Param("s") SchoolPojo schoolPojo);

    @Select("select * from school where schoolNo = #{schoolNo}")
    SchoolPojo getBySchoolNo (@Param("schoolNo") String schoolNo);

    @Select("select * from school")
    List<SchoolPojo> getAll();

    @Select("select * from school where schoolName like '%#{schoolName}%'")
    List<SchoolPojo> getBySchoolNameLike(@Param("schoolName")String schoolName);

    @Select("select * from school where schoolType like '%#{schoolType}%'")
    List<SchoolPojo> getBySchoolTypeLike(@Param("schoolType")String schoolType);

    @Select("select * from school where schoolLevel like '%#{schoolLevel}%'")
    List<SchoolPojo> getBySchoolLevelLike(@Param("schoolLevel")String schoolLevel);

    @Select("select * from school where address like '%#{address}%'")
    List<SchoolPojo> getByAddressLike(@Param("address")String address);

    @Delete("delete from school where schoolNo = #{schoolNo}")
    boolean delete(@Param("schoolNo") String schoolNo);

}


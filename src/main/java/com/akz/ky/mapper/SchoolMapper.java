package com.akz.ky.mapper;

import com.akz.ky.pojo.SchoolPojo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SchoolMapper {
    @Insert("<script>insert into school " +
            "set name = #{s.name}," +
            "schoolCode = #{s.schoolCode}," +
            "schoolType = #{s.schoolType}," +
            "schoolInfo = #{s.schoolInfo}," +
            "schoolAddress = #{s.schoolAddress}" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean add(@Param("s") SchoolPojo schoolPojo);

    @Update({"<script>update school " +
            "set name = #{s.name}," +
            "schoolCode = #{s.schoolCode}" +
            "schoolType = #{s.schoolType}," +
            "schoolInfo = #{s.schoolInfo}," +
            "schoolAddress = #{s.schoolAddress}" +
            "</script>"})
    boolean update(@Param("s") SchoolPojo schoolPojo);
}

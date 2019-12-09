package com.akz.ky.mapper;

import com.akz.ky.pojo.FirstCoursePojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/10 11:53
 * @Description
 */
@Mapper
public interface FirstCourseMapper {
    @Insert({"<script>insert into firstCourse set firstCourseCode = #{f.code},firstCourseName = #{f.name}</script>"})
//    @Options(useGeneratedKeys = true,keyProperty = "Id")
    boolean add(@Param("f") FirstCoursePojo firstCoursePojo);

    @Delete("<script>delete from firstCourse where firstCourseNo = #{id}</script>")
    boolean delete(@Param("id") int id);

    @Select("<script>select * from firstCourse where firstCourseNo = #{id}</script>")
    FirstCoursePojo getByFirstCourseNo(@Param("id") int id);

    @Select("<script>select * from firstCourse where firstCourseName like '%${name}%'</script>")
    List<FirstCoursePojo> getByNameLike(@Param("name") String name);

    @Select("<script>select * from firstCourse where firstCourseName = #{name}</script>")
    List<FirstCoursePojo> getByName(@Param("name") String name);

    @Select("<script>select * from firstCourse where firstCourseCode = #{code}</script>")
    FirstCoursePojo getByCode(@Param("code") String code);

    @Select("<script>select * from firstCourse where firstCourseCode = #{code} and firstCourseName = #{name}</script>")
    FirstCoursePojo getByCodeAndName(@Param("code") String code, @Param("name") String name);

    @Select("<script>select * from firstCourse where firstCourseType = #{type}</script>")
    List<FirstCoursePojo> listByType(@Param("type") String type);

    @Select("<script>select * from firstCourse</script>")
    List<FirstCoursePojo> listByAll();

    @Update({"<script>update firstCourse <set>" +
            "<if test = 'c.firstCourseName!=null'>firstCourseName = #{c.firstCourseName},</if>" +
            "<if test = 'c.firstCourseCode!=null'>firstCourseCode = #{c.firstCourseCode},</if>" +
            "</set> where firstCourseNo = #{c.firstCourseNo}" +
            "</script>"})
    boolean updateFirstCourse(@Param("c") FirstCoursePojo firstCoursePojo);
}

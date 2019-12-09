package com.akz.ky.mapper;

import com.akz.ky.pojo.SecondCoursePojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 9:57
 * @Description
 */
@Mapper
public interface SecondCourseMapper {

    @Select("<script>select * from secondCourse where secondCourseNo = #{secondCourseNo}</script>")
    SecondCoursePojo getById(@Param("secondCourseNo") int secondCourseNo);

    @Select("<script>select * from secondCourse where secondCourseCode = #{code}</script>")
    SecondCoursePojo getByCode(@Param("code") String code);

    @Select("<script>select * from secondCourse where secondCourseCode like '%#{code}%'</script>")
    List<SecondCoursePojo> getByLikeCode(@Param("code") String code);


    @Select("<script>select * from secondCourse where secondCourseCode like '%#{name}%'</script>")
    List<SecondCoursePojo> getByName(@Param("name") String name);

    @Select("<script>select * from secondCourse</script>")
    List<SecondCoursePojo> getAll();

    @Select("<script>select * from secondCourse where firstCourseCode = #{fc}</script>")
    List<SecondCoursePojo> getByFirstCourseCode(@Param("fc") String firstCode);

    @Delete("<script>delete from secondCourse where secondCourseNo = #{secondCourseNo} </script>")
    boolean delete(@Param("secondCourseNo") int secondCourseNo);

    @Delete({"<script>insert into secondCourse set code = #{sc.code},name = #{sc.name},majorType=#{sc.majorType},firstCode = #{sc.firstCode}</script>"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="secondCourseNo")
    boolean add(@Param("sc") SecondCoursePojo secondCoursePojo);

    @Update({"<script>update secondCourse set code = #{sc.code},name = #{sc.name},firstCode = #{sc.firstCode},majorType = #{sc.majorType} where id = #{sc.secondCourseNo} </script>"})
    boolean update(@Param("sc") SecondCoursePojo secondCoursePojo);
}

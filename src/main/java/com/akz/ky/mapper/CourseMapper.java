package com.akz.ky.mapper;

import com.akz.ky.pojo.CoursePojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert({"<script>insert into course set name = #{c.name},code = #{c.code},type = #{c.type}</script>"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean add(@Param("c") CoursePojo coursePojo);

    @Delete("<script>delete from course where id = #{id}</script>")
    boolean delete(@Param("id") int id);

    @Select("<script>select * from course where id = #{id}</script>")
    CoursePojo getById(@Param("id") int id);

    @Select("<script>select * from course where name like '%${name}%'</script>")
    List<CoursePojo> getByName(@Param("name") String name);

    @Select("<script>select * from course where code = #{code}</script>")
    CoursePojo getByCode(@Param("code") String code);

    @Select("<script>select * from course where type = #{type}</script>")
    List<CoursePojo> listByType(@Param("type") String type);

    @Select("<script>select * from course</script>")
    List<CoursePojo> listByAll();

    @Update({"<script>update course <set>" +
            "<if test = 'c.name!=null'>name = #{c.name},</if>" +
            "<if test = 'c.code!=null'>code = #{c.code},</if>" +
            "<if test = 'c.type!=null'>type = #{c.type},</if>" +
            "</set> " +
            "</script>"})
    boolean updateCourse(@Param("c") CoursePojo coursePojo);

}

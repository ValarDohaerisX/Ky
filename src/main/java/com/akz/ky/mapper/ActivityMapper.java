package com.akz.ky.mapper;

import com.akz.ky.pojo.ActivityPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author akz
 * @date 2019/09/06
 */

@Mapper
public interface ActivityMapper {

    @Insert("<script>insert into activity " +
            "set name = #{a.name}," +
            "grade = #{a.grade}," +
            "level = #{a.level}" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean add(@Param("a") ActivityPojo activityPojo);
    @Select("<script>select * from activity where 1=1 " +
            "<if test='a.name !=null'> and name = #{a.name}</if>" +
            "<if test='a.grade !=null'> and name = #{a.grade}</if>" +
            "<if test='a.level !=null'> and name = #{a.level}</if>" +
            "</script>")
    ActivityPojo get(@Param("a") ActivityPojo activityPojo);

    @Select("<script>select * from activity</script>")
    List<ActivityPojo> list();
}

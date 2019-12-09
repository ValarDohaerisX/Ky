package com.akz.ky.mapper;

import com.akz.ky.pojo.AlgorithmPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlgorithmMapper {
    @Insert("<script>insert into algorithm " +
            "set param = #{am.param}," +
            "paramName = #{am.paramName}," +
            "type = #{am.type}" +
            "</script>")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean add(@Param("am") AlgorithmPojo algorithmPojo);

    @Select("<script>select * from algorithm where id = #{id}</script>")
    AlgorithmPojo getById(@Param("id") int id);

    @Select("<script>select * from algorithm where type = #{type}</script>")
    List<AlgorithmPojo> listByType(@Param("type") String type);

    @Select("<script>select * from algorithm where 1=1</script>")
    List<AlgorithmPojo> list();

    @Delete("<script>delete from algorithm where id = #{id}</script>")
    boolean delete(@Param("id") int id);
}

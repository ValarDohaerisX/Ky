package com.akz.ky.mapper;

import com.akz.ky.pojo.ChildTiePojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020.4.1 20:13
 * @Description
 */
@Mapper
public interface ChildTieMapper {
    @Insert("insert into childtie set comment = #{ct.comment},userNo=#{ct.userNo},userName=#{ct.userName},good=#{ct.good},bad=#{ct.bad}," +
            "createdTime=#{ct.createdTime},mainTieNo=#{ct.mainTieNo},childTieNoo=#{ct.childTieNoo}")
    @Options(useGeneratedKeys=true, keyProperty="ct.childTieNo", keyColumn="childTieNo")
    boolean add(@Param("ct") ChildTiePojo childTiePojo);

    @Select("select * from childtie where childTieNo = #{childTieNo}")
    ChildTiePojo get(@Param("childTieNo") int childTieNo);

    @Select("select * from childtie where mainTieNo = #{mainTieNo}")
    List<ChildTiePojo> getChidByMainTieNo(@Param("mainTieNo") int mainTieNo);

    @Select("select * from childtie")
    List<ChildTiePojo> getAll();
}

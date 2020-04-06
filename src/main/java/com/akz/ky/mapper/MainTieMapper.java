package com.akz.ky.mapper;

import com.akz.ky.pojo.MainTiePojo;
import com.akz.ky.pojo.MajorPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/3/30 21:30
 * @Description
 */
@Mapper
public interface MainTieMapper {

    @Insert({"insert into maintie set mainTieNo = #{mj.mainTieNo},title = #{mj.title},content = #{mj.content}," +
            "userNo = #{mj.userNo},userName = #{mj.userName},reviewNum = #{mj.reviewNum},goodNum=#{mj.goodNum},badNum=#{mj.badNum}," +
            "createdTime=#{mj.createdTime},lastReplyTime=#{mj.lastReplyTime}"})
    @Options(useGeneratedKeys=true, keyProperty="mj.mainTieNo", keyColumn="mainTieNo")
    boolean add(@Param("mj") MainTiePojo mainTiePojo);

    @Select("select * from maintie")
    List<MainTiePojo> getMainTie();

    @Insert({"update maintie set title = #{mj.title},content = #{mj.content}," +
            "userNo = #{mj.userNo},userName = #{mj.userName},reviewNum = #{mj.reviewNum},goodNum=#{mj.goodNum},badNum=#{mj.badNum}," +
            "createdTime=#{mj.createdTime},lastReplyTime=#{mj.lastReplyTime} where mainTieNo = #{mj.mainTieNo}"})
    boolean update(@Param("mj") MainTiePojo mainTiePojo);

    @Select("select * from maintie where mainTieNo = #{mainTieNo}")
    MainTiePojo getOne(@Param("mainTieNo") int mainTieNo);
}

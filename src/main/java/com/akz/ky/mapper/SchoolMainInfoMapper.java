package com.akz.ky.mapper;

import com.akz.ky.pojo.SchoolMainInfoPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/21 16:39
 * @Description
 */
@Mapper
public interface SchoolMainInfoMapper {

    /**
     * 添加院校相关信息
     * @param schoolMainInfoPojo
     * @return boolean
     */
    @Insert("insert into schoolmaininfo set " +
            "infoType = #{smi.infoType},infoTitle = #{smi.infoTitle},infoContent = #{smi.infoContent},schoolNo = #{smi.schoolNo},createDate = #{smi.createDate}," +
            "createTime = #{smi.createTime},modifyDate = #{smi.modifyDate},modifyTime = #{smi.modifyTime}")
    public boolean add(@Param("smi")SchoolMainInfoPojo schoolMainInfoPojo);

    /**
     * 根据信息号修改院校相关信息
     * @param schoolMainInfoPojo
     * @return boolean
     */
    @Update("<script>update schoolmaininfo set  " +
            "<if test = 'smi.infoType !=null'>infoType = #{smi.infoType},</if>" +
            "<if test = 'smi.infoTitle !=null'>infoTitle = #{smi.infoTitle},</if>" +
            "<if test = 'smi.infoContent !=null'>infoContent = #{smi.infoContent},</if>" +
            "<if test = 'smi.schoolNo !=null'>schoolNo = #{smi.schoolNo},</if>" +
            "<if test = 'smi.createDate !=null'>createDate = #{smi.createDate},</if>" +
            "<if test = 'smi.createTime !=null'>createTime = #{smi.createTime},</if>" +
            "<if test = 'smi.modifyDate !=null'>modifyDate = #{smi.modifyDate},</if>" +
            "<if test = 'smi.modifyTime !=null'>modifyTime = #{smi.modifyTime},</if>" +
            " where infoNo = #{smi.info}</script>")
    public boolean update(@Param("smi")SchoolMainInfoPojo schoolMainInfoPojo);

    /**
     * 根据信息号和学校编号查询院校单条信息
     * @param infoNo
     * @return SchoolMainInfoPojo
     */
    @Select("select * from schoolmaininfo where infoNo = #{infoNo} and schoolNo = #{schoolNo}")
    public SchoolMainInfoPojo getByInfoNoAndSchoolNo(@Param("infoNo")String infoNo,@Param("schoolNo")String schoolNo);

    /**
     * 根据学校编号查询院校所有相关信息
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getBySchoolNo(@Param("schoolNo")String schoolNo);

    /**
     * 根据信息类型和学校编号查询院校相关信息（经济使用版）
     * @param infoType
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = #{infoType} and schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getByInfoType(@Param("infoType")String infoType,@Param("schoolNo")String schoolNo);

    /**
     * 根据关键字:describe和学校编号查询学校简介
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = 'describe' and schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getByDescribe(@Param("schoolNo")String schoolNo);

    /**
     * 根据关键字:title和学校编号查询院校公告
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = 'title' and schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getByTitle(@Param("schoolNo")String schoolNo);

    /**
     * 根据关键字:getstudent和学校编号查询招生简章
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = 'getstudent' and schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getByGetStudent(@Param("schoolNo")String schoolNo);

    /**
     * 根据关键字:dispensing和学校编号查询调剂信息
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = 'dispensing' and schoolNo = #{schoolNo}")
    public List<SchoolMainInfoPojo> getByDispensing(@Param("schoolNo")String schoolNo);

    /**
     * 根据关键字:scholarship和学校编号查询学费及奖助学金
     * @param schoolNo
     * @return List<SchoolMainInfoPojo>
     */
    @Select("select * from schoolmaininfo where infoType = 'scholarship' and #{schoolNo}")
    public List<SchoolMainInfoPojo> getByScholalShip(@Param("schoolNo")String schoolNo);

    @Delete("delete from schoolmaininfo where infoNo = #{infoNo}")
    public boolean delete(@Param("infoNo")String infoNo);

    @Select("select * from schoolmaininfo")
    public List<SchoolMainInfoPojo> getByAll();

}

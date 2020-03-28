package com.akz.ky.mapper;

import com.akz.ky.pojo.MajorIndexRequestPojo;
import com.akz.ky.pojo.MajorPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/10/25 9:57
 * @Description
 */
@Mapper
public interface MajorMapper {
    @Select("select * from major where firstCId = #{fid} and secondCId = #{sid}")
    List<MajorPojo> listByFidAndSid(@Param("fid") String fid, @Param("sid") String sid);

    @Select("select * from major where firstCId = #{fid} and secondCId = #{sid} and schoolId = #{schoolId}")
    List<MajorPojo> listByAll(@Param("fid") String fid, @Param("sid") String sid, @Param("schoolId") String schoolId);

    @Insert({"insert into major set majorCode = #{mj.majorCode},majorName = #{mj.majorName},majorType = #{mj.majorType}," +
            "techName = #{mj.techName},techMobile = #{mj.techMobile},majorLowScoreLine=#{mj.majorLowScoreLine},majorLowScore=#{mj.majorLowScore}," +
            "majorHighScore=#{mj.majorHighScore},examNum=#{mj.examNum},receivedNum=#{mj.examNum},scoreNumber=#{mj.scoreNumber},currYear=#{mj.currYear},schoolNo=#{mj.schoolNo},firstCourseNo=#{mj.firstCourseNo},secondCourseNo=#{mj.secondCourseNo}"})
    @Options(useGeneratedKeys=true, keyProperty="mj.majorNo", keyColumn="majorNo")
    boolean add(@Param("mj") MajorPojo majorPojo);

    @Select("select * from major where majorCode = #{majorCode} and majorName = #{majorName}")
    MajorPojo isExist(@Param("majorCode")String majorCode,@Param("majorName") String majorName);

    @Select("select * from major where majorCode = #{majorCode}")
    List<MajorPojo> getByMajorCode(@Param("majorCode")String majorCode);

    @Select("select * from major where firstCourseNo = #{firstCourseNo} and secondCourseNo = #{secondCourseNo}")
    List<MajorPojo> listByFS(@Param("firstCourseNo") int firstCourseNo, @Param("secondCourseNo") int secondCourseNo);

    @Update("<script>update major set " +
            "<if test='mj.majorCode!=null'>majorCode = #{mj.majorCode}, </if>" +
            "<if test='mj.majorName!=null'>majorName = #{mj.majorName}, </if>" +
            "<if test='mj.majorType!=null'>majorType = #{mj.majorType}, </if>" +
            "<if test='mj.techName!=null'>techName = #{mj.techName}, </if>" +
            "<if test='mj.techMobile!=null'>techMobile = #{mj.techMobile}, </if>" +
            "<if test='mj.majorLowScoreLine!=null'>majorLowScoreLine=#{mj.majorLowScoreLine}, </if>" +
            "<if test='mj.majorLowScore!=null'>majorLowScore=#{mj.majorLowScore}, </if>" +
            "<if test='mj.majorHighScore!=null'>majorHighScore=#{mj.majorHighScore}, </if>" +
            "<if test='mj.examNum!=null'>examNum=#{mj.examNum}, </if>" +
            "<if test='mj.receivedNum!=null'>receivedNum=#{mj.receivedNum}, </if>" +
            "<if test='mj.scoreNumber!=null'>scoreNumber=#{mj.scoreNumber}, </if>" +
            "<if test='mj.currYear!=null'>currYear=#{mj.currYear}, </if>" +
            "<if test='mj.schoolNo!=null'>schoolNo=#{mj.schoolNo}, </if>" +
            "<if test='mj.firstCourseNo!=null'>firstCourseNo=#{mj.firstCourseNo}, </if>" +
            "<if test='mj.secondCourseNo!=null'>secondCourseNo=#{mj.secondCourseNo} </if>" +
            " where majorNo = #{mj.majorNo}</script>")
    boolean updateMajor(@Param("mj")MajorPojo majorPojo);

    @Delete("delete from major where majorNo = #{majorNo}")
    boolean deleteMajor(@Param("majorNo") int majorNo);

    @Select("select *  from major where majorNo = #{majorNo}")
    MajorPojo getByMajorNo(@Param("majorNo") int majorNo);

    @Select("select *  from major where majorName like concat('%',#{majorName},'%')")
    MajorPojo getByMajorName(@Param("majorName") String majorName);

    @Select("select *  from major where schoolNo = #{schoolNo}")
    List<MajorPojo> getBySchoolNo(@Param("schoolNo") int schoolNo);

    @Select("select *  from major ")
    List<MajorPojo> getAll();

    @Select("select c.majorCode,c.majorName,c.majorType,a.schoolCode,a.schoolName,d.firstCourseCode,d.firstCourseName,e.secondCourseCode,e.secondCourseName " +
            "from school a INNER JOIN (select * from  major a where a.schoolNo is not null GROUP BY majorCode HAVING count(majorCode)>1) b  " +
            "INNER JOIN major c INNER JOIN firstcourse d INNER JOIN secondcourse e on b.majorCode = c.majorCode and c.schoolNo = a.schoolNo " +
            "and c.firstCourseNo = d.firstCourseNo and c.secondCourseNo = e.secondCourseNo ORDER BY c.majorCode ")
    List<MajorIndexRequestPojo> majorIndexRequest();
}

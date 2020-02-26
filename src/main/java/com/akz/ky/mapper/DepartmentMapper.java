package com.akz.ky.mapper;

import com.akz.ky.pojo.DepartmentPojo;
import com.akz.ky.pojo.MajorPojo;
import com.akz.ky.pojo.SchoolMainInfoPojo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2020/1/10 18:32
 * @Description
 */
@Mapper
public interface DepartmentMapper {

    @Insert({"insert into department set departmentNo = #{d.departmentNo},departmentName = #{d.departmentName},schoolNo = #{d.schoolNo}," +
            "schoolName = #{d.schoolName},majorNo = #{d.majorNo},majorName=#{d.majorName},majorCode=#{d.majorCode} "})
    @Options(useGeneratedKeys=true, keyProperty="d.departmentId", keyColumn="departmentId")
    boolean add(@Param("d") DepartmentPojo departmentPojo);

    @Select("select * from department where schoolNo = #{schoolNo}")
    public List<DepartmentPojo> getBySchoolNo(@Param("schoolNo")String schoolNo);

    @Select("select * from department where departmentNo = #{departmentNo}")
    public List<DepartmentPojo> getByDepartmentNo(@Param("departmentNo")String departmentNo);

    @Select("select * from department where departmentId = #{departmentId}")
    public DepartmentPojo getByDepartmentId(@Param("departmentId")String departmentId);

    @Update("<script>update department set  " +
            "<if test = 'd.departmentNo !=null'>departmentNo = #{d.departmentNo},</if>" +
            "<if test = 'd.departmentName !=null'>departmentName = #{d.departmentName},</if>" +
            "<if test = 'd.schoolNo !=null'>schoolNo = #{d.schoolNo},</if>" +
            "<if test = 'd.schoolName !=null'>schoolName = #{d.schoolName},</if>" +
            "<if test = 'd.majorNo !=null'>majorNo = #{d.majorNo},</if>" +
            "<if test = 'd.majorName !=null'>majorName = #{d.majorName},</if>" +
            "<if test = 'd.majorCode !=null'>majorCode = #{d.majorCode}</if>" +
            " where departmentId = #{d.departmentId}</script>")
    public boolean update(@Param("d")DepartmentPojo departmentPojo);

    /**
     * 删除单条数据
     * @param departmentId
     * @return boolean
     */
    @Delete("delete from department where departmentId = #{departmentId}")
    public boolean deleteDepartmentId(@Param("departmentId")int departmentId);

    /**
     * 批量删除单个院系及所包含的专业信息
     * @param departmentNo
     * @return boolean
     */
    @Delete("delete from department where departmentNo = #{departmentNo}")
    public boolean deleteByDepartmentNo(@Param("departmentNo")int departmentNo);

    /**
     * 删除单个院系所在的单条专业信息
     * @param majorNo
     * @return boolean
     */
    @Delete("delete from department where majorNo = #{majorNo}")
    public boolean deleteByMajorNo(@Param("majorNo")int majorNo);

}

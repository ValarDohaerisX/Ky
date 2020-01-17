package com.akz.ky.mapper;

import com.akz.ky.pojo.LdcodePojo;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/10 19:32
 * @Description
 */
@Mapper
public interface LdcodeMapper {
    @Select("select * from ldcode where codeType = #{codeType} order by code asc")
    public List<LdcodePojo> getLdCodeByCodeType(@Param("codeType") String codeType);

    @Select("select * from ldcode where codeType = 'schooltype' ")
    public List<LdcodePojo> getLdCodeBySchoolType();

    @Select("select * from ldcode where codeType = 'schoollevel' ")
    public List<LdcodePojo> getLdCodeBySchoolLevel();

    @Select("select * from ldcode where codeType = 'address' ")
    public List<LdcodePojo> getLdCodeByAddress();

    @Select("select * from ldcode where codeType = #{codeType} and code = #{code}")
    public List<LdcodePojo> getLdCodeByCodeTypeAndCode(@Param("codeType")String codeType, @Param("code") String code);


}

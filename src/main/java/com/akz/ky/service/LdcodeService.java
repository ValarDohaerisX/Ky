package com.akz.ky.service;

import com.akz.ky.message.Result;
import com.akz.ky.pojo.LdcodePojo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 17:22
 * @Description
 */

public interface LdcodeService {

    public List<LdcodePojo> getLdCodeByCodeType(String codeType);

    public List<LdcodePojo> getLdCodeBySchoolType();

    public List<LdcodePojo> getLdCodeBySchoolLevel();

    public List<LdcodePojo> getLdCodeByAddress();

    public List<LdcodePojo> getLdCodeByCodeTypeAndCode(String codeType,  String code);

}

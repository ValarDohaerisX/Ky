package com.akz.ky.service.impl;

import com.akz.ky.mapper.LdcodeMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.LdcodePojo;
import com.akz.ky.service.LdcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 17:23
 * @Description 基础信息服务类-地区信息/专业类型信息/院校类型信息..
 */
@Service
@CacheConfig(cacheNames = "ldcodes")
public class LdcodeServiceImpl implements LdcodeService {

    @Autowired(required = false)
    LdcodeMapper ldcodeMapper;

    @Override
    @Cacheable(key="'ldcodes-byCodeTypes-'+ #p0")
    public List<LdcodePojo> getLdCodeByCodeType(String codeType) {
        return ldcodeMapper.getLdCodeByCodeType(codeType);
    }

    @Override
    @Cacheable(key="'ldcodes-bySchoolTypes'")
    public List<LdcodePojo> getLdCodeBySchoolType() {
        return ldcodeMapper.getLdCodeBySchoolType();
    }

    @Override
    @Cacheable(key="'ldcodes-bySchoolLevels'")
    public List<LdcodePojo> getLdCodeBySchoolLevel() {
        return ldcodeMapper.getLdCodeBySchoolLevel();
    }

    @Override
    @Cacheable(key="'ldcodes-byAddress'")
    public List<LdcodePojo> getLdCodeByAddress() {
        return ldcodeMapper.getLdCodeByAddress();
    }

    @Override
    @Cacheable(key="'ldcodes-byCodeTypeAndCodes-'+ #p0+ '-' + #p1")
    public List<LdcodePojo> getLdCodeByCodeTypeAndCode(String codeType, String code) {
        return ldcodeMapper.getLdCodeByCodeTypeAndCode(codeType, code);
    }
}

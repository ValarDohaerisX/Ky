package com.akz.ky.service.impl;

import com.akz.ky.mapper.LdcodeMapper;
import com.akz.ky.message.ApiReturnCode;
import com.akz.ky.message.Result;
import com.akz.ky.pojo.LdcodePojo;
import com.akz.ky.service.LdcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzx
 * @version 1.0
 * @date 2019/12/27 17:23
 * @Description
 */
@Service
public class LdcodeServiceImpl implements LdcodeService {

    @Autowired(required = false)
    LdcodeMapper ldcodeMapper;

    @Override
    public List<LdcodePojo> getLdCodeByCodeType(String codeType) {
        return ldcodeMapper.getLdCodeByCodeType(codeType);
    }

    @Override
    public List<LdcodePojo> getLdCodeBySchoolType() {
        return ldcodeMapper.getLdCodeBySchoolType();
    }

    @Override
    public List<LdcodePojo> getLdCodeBySchoolLevel() {
        return ldcodeMapper.getLdCodeBySchoolLevel();
    }

    @Override
    public List<LdcodePojo> getLdCodeByAddress() {
        return ldcodeMapper.getLdCodeByAddress();
    }

    @Override
    public List<LdcodePojo> getLdCodeByCodeTypeAndCode(String codeType, String code) {
        return ldcodeMapper.getLdCodeByCodeTypeAndCode(codeType, code);
    }
}

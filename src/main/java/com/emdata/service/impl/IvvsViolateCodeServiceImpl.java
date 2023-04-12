package com.emdata.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emdata.mapper.ivvs.IvvsViolateCodeMapper;
import com.emdata.service.IvvsViolateCodeService;
import com.emdata.model.dao.IvvsViolateCode;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 违法行为算法枚举对照表 服务实现类
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-18
 */
@Service
public class IvvsViolateCodeServiceImpl extends ServiceImpl<IvvsViolateCodeMapper, IvvsViolateCode> implements IvvsViolateCodeService {


    @Override
    public String getThirdPartyCode(String code) {
        IvvsViolateCode record = this.getOne(Wrappers.<IvvsViolateCode>lambdaQuery().eq(IvvsViolateCode::getCode , code).eq(IvvsViolateCode::getDel , 0));
        if (Objects.nonNull(record)) {
            if (StrUtil.isNotBlank(record.getThirdPartyCode())) {
                return record.getThirdPartyCode();
            }
        }
        return null;
    }
}

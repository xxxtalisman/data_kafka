package com.emdata.service;

import com.emdata.model.dao.IvvsViolateCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 违法行为算法枚举对照表 服务类
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-18
 */
public interface IvvsViolateCodeService extends IService<IvvsViolateCode> {


    /***
     * 获取发送第三方代码
     * @param code
     * @return
     */
    String getThirdPartyCode(String code);
}

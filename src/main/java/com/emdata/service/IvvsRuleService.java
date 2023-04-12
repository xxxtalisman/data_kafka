package com.emdata.service;

import java.util.List;

/**
 *
 * @Author:
 * @CreateDate: 2019/12/19
 * @Version: 1.0
 */
public interface IvvsRuleService {

    /**
     * 获取数据接入限制设备编号
     *
     * @author
     * @Param []
     */
    List<String> getDataInsertDeviceRule();

    /**
     * 获取数据接入限制违法行为
     *
     * @author
     * @Param []
     */
    List<String> getDataInsertIllegalActRule();
}

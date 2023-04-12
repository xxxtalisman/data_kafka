package com.emdata.strategy;

import com.emdata.common.util.RespRet;
import com.emdata.model.dto.RecordDataDTO;

/**
 * @Date: 2019/12/12 9:18
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
public interface IDataTarget {

    /***
     * 获取第三方
     * @param dto 数据对象
     * @param cityCode 城市代码
     * @return
     */
    RespRet action(RecordDataDTO dto , String cityCode);

}

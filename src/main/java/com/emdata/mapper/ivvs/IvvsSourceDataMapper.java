package com.emdata.mapper.ivvs;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emdata.model.dao.IvvsSourceData;

import java.util.List;

/**
 * <p>
 * 违法数据接入表 Mapper 接口
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-18
 */
public interface IvvsSourceDataMapper extends BaseMapper<IvvsSourceData> {



    /**
     * 批量插入获取原始数据
     * @param list
     * @return
     */
    int batchInsert(List<IvvsSourceData> list);
    
    
    /**
     * 单挑插入sourceData表
     * @param obj
     * @return
     */
    Integer saveSourceData(IvvsSourceData obj);
}

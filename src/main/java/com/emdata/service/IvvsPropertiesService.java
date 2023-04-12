package com.emdata.service;

import com.emdata.model.dao.IvvsProperties;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 属性表 服务类
 * </p>
 *
 * @author pengqingfeng
 * @since 2019-12-12
 */
public interface IvvsPropertiesService extends IService<IvvsProperties> {


    /***
     * 初始化属性
     * @return
     */
    boolean loadProperties();
}

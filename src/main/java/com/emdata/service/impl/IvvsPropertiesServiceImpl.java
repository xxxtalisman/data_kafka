package com.emdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emdata.common.enums.PropertyKeyEnum;
import com.emdata.mapper.ivvs.SysDefaultCityConfigMapper;
import com.emdata.model.dao.IvvsProperties;
import com.emdata.common.util.BusinessHelper;
import com.emdata.mapper.ivvs.IvvsPropertiesMapper;
import com.emdata.model.dao.SysDefaultCityConfig;
import com.emdata.service.IvvsPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author txw
 * @since 2019-12-12
 */
@Slf4j
@Service
public class IvvsPropertiesServiceImpl extends ServiceImpl<IvvsPropertiesMapper, IvvsProperties> implements IvvsPropertiesService {


    @Resource
    SysDefaultCityConfigMapper defaultCityConfigMapper;

    /***
     * 加载属性到内存中
     * @return
     */
    @Override
    public boolean loadProperties() {
        String cityCode = "";
        SysDefaultCityConfig defaultCityConfig = defaultCityConfigMapper.selectOne( new QueryWrapper<>());
        cityCode = StrUtil.isBlank(defaultCityConfig.getDistrict()) ? defaultCityConfig.getCity() : defaultCityConfig.getDistrict();
        BusinessHelper.putBaseProps(PropertyKeyEnum.CITY_CODE.getKey(), cityCode);
        BusinessHelper.printBaseProps();
        BusinessHelper.printCityProps();
        return true;
    }
}

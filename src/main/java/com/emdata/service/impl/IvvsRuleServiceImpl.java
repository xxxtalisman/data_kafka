package com.emdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emdata.common.enums.*;
import com.emdata.mapper.ivvs.IvvsSbbhinfoMapper;
import com.emdata.mapper.ivvs.IvvsViolateCodeMapper;
import com.emdata.model.dao.IvvsSbbhinfo;
import com.emdata.model.dao.IvvsViolateCode;
import com.emdata.service.IvvsRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 获取不接数据设备和违法行为
 *
 * @Author: txw
 * @CreateDate: 2019/11/1 13:49
 * @Version: 1.0
 */
@Service
public class IvvsRuleServiceImpl implements IvvsRuleService {

    @Resource
    private IvvsSbbhinfoMapper sbbhinfoMapper;

    @Resource
    private IvvsViolateCodeMapper violateCodeMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public List<String> getDataInsertDeviceRule() {
        List<String> sbbhs = (List)redisTemplate.opsForValue().get(RedisKeyEnum.DATA_INSERT_DEVICE_RULE.getKey());
        if (CollectionUtil.isNotEmpty(sbbhs)) {
            return sbbhs;
        }
        final List<String> sbbhList = new ArrayList<>();
        QueryWrapper<IvvsSbbhinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_state", AccessStateEnum.ENABLE.getCode());
        queryWrapper.eq("status", DeviceStatusEnum.NORMAL.getCode());
        List<IvvsSbbhinfo> list = sbbhinfoMapper.selectList(queryWrapper);
        list.forEach(sbbhinfo -> sbbhList.add(sbbhinfo.getSbbh()));
        redisTemplate.opsForValue().set(RedisKeyEnum.DATA_INSERT_DEVICE_RULE.getKey(),sbbhList,5*60*1000);
        return sbbhList;
    }

    @Override
    public List<String> getDataInsertIllegalActRule() {
        List<String > wfxws = (List) redisTemplate.opsForValue().get(RedisKeyEnum.DATA_INSERT_ILLEGAL_ACT_RULE.getKey());
        if (CollectionUtil.isNotEmpty(wfxws)) {
            return wfxws;
        }
        List<String> wfxwList = new ArrayList<>();
        QueryWrapper<IvvsViolateCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del", DataStatusEnum.NORMAL.getCode());
        queryWrapper.eq("receive_status", ReceiveStateEnum.ENABLE.getCode());
        List<IvvsViolateCode> list = violateCodeMapper.selectList(queryWrapper);
        list.forEach(violateCode -> wfxwList.add(violateCode.getCode()));
        redisTemplate.opsForValue().set(RedisKeyEnum.DATA_INSERT_ILLEGAL_ACT_RULE.getKey(),wfxwList,5*60*1000);
        return wfxwList;
    }
}

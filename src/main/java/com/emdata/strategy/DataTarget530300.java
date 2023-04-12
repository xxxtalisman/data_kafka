package com.emdata.strategy;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.emdata.common.util.Constant;
import com.emdata.common.util.RespRet;
import com.emdata.model.dao.IvvsSourceData;
import com.emdata.model.dto.RecordDataDTO;
import com.emdata.model.dto.CityReturnData530300DTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Date: 2019/12/17 13:16
 * @Version: 1.0
 * @Author: txw
 * @Description: 曲靖-kafka
 */
@Component("dataTarget530300")
public class DataTarget530300 implements IDataTarget {

    @Override
    public RespRet action(RecordDataDTO dto , String cityCode) {
        RespRet<Map<String,Object>> respRet = new RespRet<>();
        Map<String,Object> objectMap = new HashMap<>();
        try {
            //处理json数据转为映射实体
            CityReturnData530300DTO retDto = JSONObject.parseObject(dto.getRecordValue(), CityReturnData530300DTO.class);
            if(null!=retDto) {
                IvvsSourceData sourceData = new IvvsSourceData();
                //赋值
                BeanUtil.copyProperties(retDto , sourceData);
                sourceData.setWfsj(new Date());
                objectMap.put(Constant.SOURCE_DATA, sourceData);
                respRet.setData(objectMap);
            }
        }catch (Exception e){
            return RespRet.failed(StrUtil.format("{} action Exception {}", cityCode,e.getMessage()));
        }
        return respRet;
    }

}

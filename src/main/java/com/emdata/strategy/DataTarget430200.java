package com.emdata.strategy;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.emdata.common.util.Constant;
import com.emdata.common.util.RespRet;
import com.emdata.model.dao.IvvsSourceData;
import com.emdata.model.dto.RecordDataDTO;
import com.emdata.model.dao.IvvsSourceDataMore;
import com.emdata.model.dto.CityReturnData430200DTO;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


/**
 * @Date: 2019/12/26 13:16
 * @Version: 1.0
 * @Author: txw
 * @Description: 株洲-kafka
 */
@Component("dataTarget430200")
public class DataTarget430200 implements IDataTarget {

    @Override
    public RespRet action(RecordDataDTO dto , String cityCode) {
        RespRet<Map<String,Object>> respRet = new RespRet<>();
        Map<String,Object> objectMap = new HashMap<>();
        try {
            //处理json数据转为映射实体
            CityReturnData430200DTO retDto = JSONObject.parseObject(dto.getRecordValue(), CityReturnData430200DTO.class);;
            if(null!=retDto) {
                IvvsSourceData sourceData = new IvvsSourceData();
                IvvsSourceDataMore ivvsSourceDataMore = new IvvsSourceDataMore();
                //赋值
                BeanUtil.copyProperties(retDto , sourceData);
                BeanUtil.copyProperties(retDto , ivvsSourceDataMore);
                objectMap.put(Constant.SOURCE_DATA, sourceData);
                objectMap.put(Constant.SOURCE_DATA_MORE, ivvsSourceDataMore);
                respRet.setData(objectMap);
            }
        }catch (Exception e){
            return RespRet.failed(StrUtil.format("{} action Exception {}", cityCode,e.getMessage()));
        }
        return respRet;
    }

}

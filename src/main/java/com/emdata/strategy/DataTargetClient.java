package com.emdata.strategy;

import com.emdata.common.config.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 2019/12/12 9:23
 * @Version: 1.0
 * @Author: txw
 * @Description:
 */
@Slf4j
@Component
//@DependsOn表示被注解的bean在初始化时，指定的bean需要先完成初始化。
@DependsOn("applicationContextHelper")
public class DataTargetClient<T> implements InitializingBean {

    private ConcurrentHashMap<String, IDataTarget> sendTargetMap = new ConcurrentHashMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContextHelper.getBeanOfType(IDataTarget.class).forEach((key , value) -> {
            log.info("初始化SendThirdParty子类 >> {} >> {}" , key , value);
            sendTargetMap.put(key , value);
        });
    }

    public IDataTarget getSendTarget(String type) {
        return sendTargetMap.get(type);
    }
}

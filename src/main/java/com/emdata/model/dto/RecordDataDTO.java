package com.emdata.model.dto;

import lombok.Data;

/**
 * @Author: txw
 * @Date: 2019/12/26 16:09
 */

@Data
public class RecordDataDTO {

    /**
     * kafka 消费定义的key
     */
    String recordKey;


    /**
     * kafka 消费定义的值
     */
    String recordValue;

}

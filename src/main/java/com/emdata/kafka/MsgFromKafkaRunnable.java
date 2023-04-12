package com.emdata.kafka;

/**
 * @Author: txw
 * @Date: 2019/12/26 13:44
 */
public class MsgFromKafkaRunnable  implements Runnable{

    private String obj = "MsgFromKafkaRunnable";

    @Override
    public void run() {
        synchronized(obj)
        {
            //启动kafaka消费
            KafkaConsumerAdapter.run();
        }
    }
}

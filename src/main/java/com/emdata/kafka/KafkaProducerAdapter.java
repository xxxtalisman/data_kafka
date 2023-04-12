package com.emdata.kafka;

import com.emdata.kafka.security.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 默认的kafka生成者，用于往kafka中发送消息
 */
@Slf4j
public class KafkaProducerAdapter {

    private static final Properties props = new Properties();

    /**
     * Broker地址列表
     */
    private final String bootstrapServers = "bootstrap.servers";

    /**
     *  客户端ID
     */
    private final String clientId = "client.id";

    /**
     * Key序列化类
     */
    private final String keySerializer = "key.serializer";

    /**
     * Value序列化类
     */
    private final String valueSerializer = "value.serializer";

    /**
     * 协议类型:当前支持配置为SASL_PLAINTEXT或者PLAINTEXT
     */
    private final String securityProtocol = "security.protocol";

    /**
     * 服务名
     */
    private final String saslKerberosServiceName = "sasl.kerberos.service.name";

    /**
     * 域名
     */
    private final String kerberosDomainName = "kerberos.domain.name";

    /**
     * 默认发送20条消息
     */
    private final int messageNumToSend = 100;

    /**
     * 用户自己申请的机机账号keytab文件名称
     */
    private static final String USER_KEYTAB_FILE = "user.keytab";

    /**
     * kafka生产者，用于向kafka中发送消息
     */
    private static Producer<Object, Object> kafkaProducer;

    /**
     * 同步锁
     */
    private static final Object LOCK = new Object();

    /**
     * 每个topic发送的消息计数器
     */
    private final Map<String, Long> topicCounterMap = new HashMap<>();

    /**
     * kafka生产者适配器（单例），用来代理kafka生产者发送消息
     */
    private static KafkaProducerAdapter kafkaProducerAdapter;

    private KafkaProducerAdapter() throws Exception {
        initKafkaProducer();
    }


    /***
     * 获取生产者实例
     * @return
     * @throws Exception
     */
    public static KafkaProducerAdapter getInstance() throws Exception {
        if (kafkaProducerAdapter == null) {
            synchronized (LOCK) {
                if (kafkaProducerAdapter == null) {
                    kafkaProducerAdapter = new KafkaProducerAdapter();
                }
            }
        }

        return kafkaProducerAdapter;
    }

    public KafkaProducerAdapter send(String topic , Object message) {
        Long key = generateKey(topic);
        ProducerRecord<Object, Object> record = new ProducerRecord(topic , key.toString() , message);
        kafkaProducer.send(record , new DefaultCallbackImpl(record));
        return this;
    }

    public void flush() {
        if (kafkaProducer != null) {
            kafkaProducer.flush();
        }
    }

    public void close() {
        if (kafkaProducer != null) {
            kafkaProducer.close();
        }
    }

    /***
     * 初始化属性
     * @param properties
     */
    public static void initProperty(Properties properties) {
        props.putAll(properties);
    }

    /**
     * 根据kafka生产者配置信息初始化kafka消息生产者,只初始化一次
     */
    private void initKafkaProducer() throws Exception {
        try {

            //指定必须要有多少个分区的副本接收到该消息，服务端才会向生产者发送响应，可选值为：0,1,2，…，all
            props.put("acks" , "all");
            //发生错误时重试次数
            props.put("retries" , 3);
            props.put("batch.size" , 16384);
            props.put("linger.ms" , 1);
            //生产者的内存缓冲区大小。如果生产者发送消息的速度 > 消息发送到kafka的速度，那么消息就会在缓冲区堆积，导致缓冲区不足。这个时候，send()方法要么阻塞，要么抛出异常。
            props.put("buffer.memory" , 33554432);
//            props.put("key.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
//            props.put("value.serializer" , "org.apache.kafka.common.serialization.StringSerializer");

            //安全验证
            initSecurity(props);

            //实例化生产对象
            kafkaProducer = new KafkaProducer<>(props);
        } catch (Exception e) {
            log.error("根据配置文件[kafka.properties]初始化kafka生产者出现异常:" + e.getMessage() , e);
            throw e;
        }
    }


    private static void initSecurity(Properties props) {
        boolean isSecurity = Boolean.valueOf(props.getProperty("is.security"));
        if (isSecurity) {
            try {
                log.info("Securitymode start.");
                securityPrepare(props);
            } catch (IOException e) {
                log.error("Security prepare failure." , e);
            }
            log.info("Security prepare success.");
        }
    }

    private static void securityPrepare(Properties props) throws IOException {
        String filePath = props.getProperty("config.path") + File.separator;
        String krbFile = filePath + "krb5.conf";
        String userKeyTableFile = filePath + USER_KEYTAB_FILE;
        String principal = props.getProperty("user.principal");
        log.info("login user : {}" , principal);
        log.info("keytab : {}" , userKeyTableFile);
        LoginUtil.setKrb5Config(krbFile);
        LoginUtil.setZookeeperServerPrincipal("zookeeper/hadoop.hadoop.com");
        LoginUtil.setJaasFile(filePath , principal , userKeyTableFile);
    }

    /**
     * 生成待发送的消息的key
     *
     * @return message key
     */
    private Long generateKey(String topic) {
        synchronized (LOCK) {
            Long counter = topicCounterMap.get(topic);
            if (counter == null || Long.MAX_VALUE - counter <= 10) {
                counter = 0L;
            }
            ++counter;
            topicCounterMap.put(topic , counter);
            return counter;
        }
    }

    /**
     * 默认的消息回调实现
     */
    private class DefaultCallbackImpl implements Callback {

        private ProducerRecord<Object, Object> record;

        public DefaultCallbackImpl(ProducerRecord<Object, Object> record) {
            this.record = record;
        }

        @Override
        public void onCompletion(RecordMetadata recordMetadata , Exception e) {
            if (e == null) {
                if (log.isDebugEnabled()) {
                    log.debug("消息[key=" + record.key() + ",value=" + record.value() + "]发送成功." + "消息[partition=" + recordMetadata.partition() + ",offset=" + recordMetadata.offset() + "]");
                }
            } else {
                log.error("消息[key=" + record.key() + ",value=" + record.value() + "]发送失败." + e.getMessage() , e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        KafkaProducerAdapter kafkaProducerAdapter = KafkaProducerAdapter.getInstance();
        String topic = "test_ss";
        byte[] message = "yoyowon".getBytes();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            kafkaProducerAdapter.send(topic , message);
        }
        kafkaProducerAdapter.flush();
        long endTime = System.currentTimeMillis();
        System.out.println("发送10条消息共耗时：" + (endTime - startTime) + "毫秒");
        kafkaProducerAdapter.close();
    }
}

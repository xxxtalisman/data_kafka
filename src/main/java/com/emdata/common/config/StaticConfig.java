//package com.emdata.common.config;
//
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.util.ClassLoaderUtil;
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import com.emdata.common.enums.DataProcErrEnum;
//import com.emdata.common.enums.DataProcTypeEnum;
//import com.emdata.common.util.BusinessHelper;
//import com.emdata.kafka.KafkaConsumerAdapter;
//import com.emdata.kafka.KafkaProducerAdapter;
//import com.emdata.kafka.MsgFromKafkaRunnable;
//import com.emdata.model.dao.DataProcErrLog;
//import com.emdata.model.dao.IvvsSourceData;
//import com.emdata.service.DataProcErrLogService;
//import com.emdata.service.IvvsPropertiesService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.system.ApplicationHome;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//public class StaticConfig {
//
//
//    @Autowired
//    private IvvsPropertiesService ivvsPropertiesService;
//
//    @Autowired
//    private DataProcErrLogService dataProcErrLogService;
//
//    @Bean
//    public void init() {
//
//        ivvsPropertiesService.loadProperties();
//
//        initKafkaConfig();
//
//    }
//
//
//    @Value("${spring.profiles.active}")
//    private String env;
//
//    /***
//     * 初始化kafka配置
//     */
//    private void initKafkaConfig() {
//
//        try {
//            if (!StrUtil.equals(env , "dev")) {
//                env = "prod";
//            }
//            //读取当前城市下kafka配置文件 resources/config/big-data/430200
//            String relativePath = StrUtil.format("config/big-data/{}/{}" , env , BusinessHelper.getCityCode());
//            log.info("relativePath >> {}" , relativePath);
//            //循环读取resource下配置文件
//            InputStream stream = null;
//            while (true) {
//                stream = ClassLoaderUtil.getClassLoader().getResourceAsStream(relativePath + "/appConfig.properties");
//                if(null != stream){
//                   break;
//                }
//                String msg = String.format("kafka配置文件不存在,请配置当前城市%s下的kafka配置项.",BusinessHelper.getCityCode());
//                log.error(msg);
//                saveDataProcErrLog(DataProcErrEnum.KAFKA_CONFIG_EMPTY,msg);
//                Thread.sleep(50000);
//
//                //重新加载配置项,检测是否有变动
//                ivvsPropertiesService.loadProperties();
//            }
//            Properties props = new Properties();
//            //加载到props中
//            props.load(stream);
//
//            generateSecurityFile(relativePath, props);
//
//            //初始化参数
//            KafkaConsumerAdapter.initProperty(props);
//
//            //启动消费者消费数据
//            MsgFromKafkaRunnable kfkRunning = new MsgFromKafkaRunnable();
//            Thread thread = new Thread(kfkRunning);
//            thread.start();
//
//        } catch (Exception e) {
//            log.error(e.getMessage() , e);
//        }
//    }
//
//    /**
//     * 错误记录保存入库
//     * @param errEnum
//     * @param addMsg
//     */
//    private void saveDataProcErrLog(DataProcErrEnum errEnum, String addMsg){
//        dataProcErrLogService.save(getDataProcErrLog(errEnum,addMsg));
//    }
//
//
//    /**
//     * 组装错误log数据记录
//     * @param errEnum
//     * @param addMsg
//     * @return
//     */
//    private DataProcErrLog getDataProcErrLog(DataProcErrEnum errEnum, String addMsg){
//        DataProcErrLog dataProcErrLog = new DataProcErrLog();
//        dataProcErrLog.setCreateTime(new Date());
//        dataProcErrLog.setErrCode(errEnum.getErrCode());
//        dataProcErrLog.setErrMsg(errEnum.getErrMsg()+":"+addMsg);
//        dataProcErrLog.setType(DataProcTypeEnum.INPUT.getCode());
//        return dataProcErrLog;
//    }
//
//    /***
//     * 生成安全验证文件
//     * @param relativePath
//     * @param props
//     * @throws IOException
//     */
//    private void generateSecurityFile(String relativePath , Properties props) throws IOException {
//        if (Boolean.valueOf(props.getProperty("is.security"))) {
//            String destPath = getJarRoot() + File.separator + "config";
//            log.info("destPath >> {}" , destPath);
//            //读取jar包内部安全认证文件，生成到jar同级目录
//            generateFile(relativePath , destPath , "krb5.conf");
//            generateFile(relativePath , destPath , "user.keytab");
//            props.put("config.path" , destPath);
//        }
//    }
//
//    private String generateFile(String relativePath , String destPath , String fileName) throws IOException {
//        InputStream inputStream = ClassLoaderUtil.getClassLoader().getResourceAsStream(relativePath + "/" + fileName);
//        byte[] buffer = new byte[inputStream.available()];
//        inputStream.read(buffer);
//        File file = FileUtil.file(destPath + File.separator + fileName);
//        FileUtil.writeBytes(buffer , file);
//        return file.getPath();
//    }
//
//    /**
//     * 获得发布后的jar当前路径
//     */
//    public String getJarRoot() {
//        ApplicationHome home = new ApplicationHome(getClass());
//        File jarFile = home.getSource();
//        return jarFile.getParentFile().getPath();
//    }
//}

package com.emdata.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 */
@Configuration
@EnableAsync
public class ThreadpoolConfig {

    //最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目
    //    @Value("${threadpool.corePoolSize}")
    private int corePoolSize = 10;

    //    @Value("${threadpool.maxPoolSize}")
    private int maxPoolSize=30;

    //    @Value("${threadpool.queueCapacity}")
    private int queueCapacity=100;

    //    @Value("${threadpool.keepAlive}")
    private int keepAlive=300;


    @Bean("threadPoolTaskExecutor")
    public Executor executorPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列容量
        executor.setQueueCapacity(queueCapacity);
        //默认线程名称
        executor.setThreadNamePrefix("kafka-Executor-");
        //拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(keepAlive);
        executor.initialize();
        //设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}

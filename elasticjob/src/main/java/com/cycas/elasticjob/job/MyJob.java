package com.cycas.elasticjob.job;

import com.cycas.elasticjob.annotation.ElasticSimpleJob;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author naxin
 * @Description:
 * @date 2020/10/2915:40
 */
@ElasticSimpleJob(cron = "0 0/1 * * * ?",
        jobName = "firstJob",
        shardingTotalCount = 1,
        jobParameter = "测试参数",
        shardingItemParameters = "0=A",
        dataSource = "datasource")
@Component
public class MyJob implements SimpleJob {

    Logger logger = LoggerFactory.getLogger(MyJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {

        logger.info(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s,当前参数: %s," +
                        "当前任务名称: %s,当前任务参数: %s,"+
                        "当前任务的id: %s"
                ,
                //获取当前线程的id
                Thread.currentThread().getId(),
                //获取任务总片数
                shardingContext.getShardingTotalCount(),
                //获取当前分片项
                shardingContext.getShardingItem(),
                //获取当前的参数
                shardingContext.getShardingParameter(),
                //获取当前的任务名称
                shardingContext.getJobName(),
                //获取当前任务参数
                shardingContext.getJobParameter(),
                //获取任务的id
                shardingContext.getTaskId()
        ));
    }


}

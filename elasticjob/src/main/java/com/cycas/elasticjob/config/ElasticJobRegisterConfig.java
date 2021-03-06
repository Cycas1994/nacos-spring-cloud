package com.cycas.elasticjob.config;

import com.cycas.elasticjob.listener.MyElasticJobListener;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author naxin
 * @Description:
 * @date 2020/10/2915:46
 */
@Configuration
@ConditionalOnExpression("'${elaticjob.zookeeper.server-lists}'.length() > 0")
public class ElasticJobRegisterConfig {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${elaticjob.zookeeper.server-lists}") String serverList,
                                             @Value("${elaticjob.zookeeper.namespace}") String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

    @Bean
    public MyElasticJobListener elasticJobListener() {
        return new MyElasticJobListener(100, 100);
    }

}

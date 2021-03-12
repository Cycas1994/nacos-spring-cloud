package com.cycas.elasticjob.config;

import com.cycas.elasticjob.annotation.ElasticSimpleJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.Map;

@Configuration
@ConditionalOnExpression("'${elaticjob.zookeeper.server-list}'.length() > 0")
public class ElasticJobAutoConfiguration {

    @Value("${elaticjob.zookeeper.server-lists}")
    private String serverList;
    @Value("${elaticjob.zookeeper.namespace}")
    private String namespace;
    @Autowired
    private ApplicationContext applicationContext;

    public ElasticJobAutoConfiguration() {}

    @PostConstruct
    public void initElasticJob() {

        ZookeeperRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(this.serverList, this.namespace));
        regCenter.init();
        Map<String, SimpleJob> map = this.applicationContext.getBeansOfType(SimpleJob.class);
        Iterator<Map.Entry<String, SimpleJob>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, SimpleJob> entry = iterator.next();
            SimpleJob simpleJob = entry.getValue();
            ElasticSimpleJob elasticSimpleJobAnnotation = simpleJob.getClass().getAnnotation(ElasticSimpleJob.class);
            String cron = StringUtils.defaultIfBlank(elasticSimpleJobAnnotation.cron(), elasticSimpleJobAnnotation.value());
            JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(simpleJob.getClass().getName(), cron, elasticSimpleJobAnnotation.shardingTotalCount())
                    .shardingItemParameters(elasticSimpleJobAnnotation.shardingItemParameters()).build();
            SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, simpleJob.getClass().getCanonicalName());
            LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
            String dataSourceRef = elasticSimpleJobAnnotation.dataSource();
            if (StringUtils.isNotBlank(dataSourceRef)) {
                if (!this.applicationContext.containsBean(dataSourceRef)) {
                    throw new RuntimeException("not exist datasource [" + dataSourceRef + "]！");
                }
                DataSource dataSource = (DataSource)this.applicationContext.getBean(dataSourceRef);
                JobEventRdbConfiguration jobEventRdbConfiguration = new JobEventRdbConfiguration(dataSource);
                SpringJobScheduler jobScheduler = new SpringJobScheduler(simpleJob, regCenter, liteJobConfiguration, jobEventRdbConfiguration, new ElasticJobListener[0]);
                jobScheduler.init();
            } else {
                SpringJobScheduler jobScheduler = new SpringJobScheduler(simpleJob, regCenter, liteJobConfiguration, new ElasticJobListener[0]);
                jobScheduler.init();
            }
        }
    }

}

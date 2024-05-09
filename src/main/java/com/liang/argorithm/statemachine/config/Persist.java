package com.liang.argorithm.statemachine.config;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

/**
 * @author liangbingtian
 * @date 2024/03/01 下午2:57
 */
@Configuration
@Slf4j
public class Persist<S, E> {

    /**
     * 持久化到内存map
     */
    public static StateMachinePersister getPersister() {
        return new DefaultStateMachinePersister(new StateMachinePersist() {
            @Override
            public void write(StateMachineContext stateMachineContext, Object o) throws Exception {
                log.info("持久化状态机,context:{},contextObject:{}", JSON.toJSONString(stateMachineContext), JSON.toJSONString(o));
                map.put(o, stateMachineContext);
            }

            @Override
            public StateMachineContext read(Object o) throws Exception {
                log.info("获取状态机,contextObj:{}", JSON.toJSONString(o));
                final StateMachineContext o1 = (StateMachineContext)map.get(o);
                log.info("获取状态机结果,stateMachineContext:{}", JSON.toJSONString(o1));
                return o1;
            }

            private Map map = new HashMap();
        });
    }

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 持久化到redis中，在分布式系统中使用
     */
    public RedisStateMachinePersister<S, E> getRedisPersister() {
        RedisStateMachineContextRepository<S, E> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        final RepositoryStateMachinePersist<S, E> persist = new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(persist);
    }

}

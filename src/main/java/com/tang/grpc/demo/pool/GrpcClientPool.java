package com.tang.grpc.demo.pool;

import com.tang.grpc.demo.client.GrpcClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class GrpcClientPool {
    private static GenericObjectPool<GrpcClient> objectPool = null;

    static {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(5);
        // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
        poolConfig.setMaxWaitMillis(-1);
        // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
        poolConfig.setLifo(true);
        // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
        poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);
        // 连接耗尽时是否阻塞,默认为true
        poolConfig.setBlockWhenExhausted(true);
        objectPool = new GenericObjectPool<>(new GrpcFactory(), poolConfig);
    }

    public static GrpcClient borrowObject() {
        try {
            GrpcClient grpcClient = objectPool.borrowObject();
            System.out.println("当前总线程：" + objectPool.getCreatedCount());
            return grpcClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


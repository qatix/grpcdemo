package com.tang.grpc.demo.pool;

import com.tang.grpc.demo.client.GrpcClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class GrpcFactory extends BasePooledObjectFactory<GrpcClient> {
    private static final String host = "127.0.0.1";
    private static final int port = 50001;

    @Override
    public GrpcClient create() throws Exception {
        return new GrpcClient(host, port);
    }

    @Override
    public PooledObject<GrpcClient> wrap(GrpcClient grpcClient) {
        return new DefaultPooledObject<>(grpcClient);
    }

    @Override
    public void destroyObject(PooledObject<GrpcClient> p) throws Exception {
        p.getObject().shutDown();
        super.destroyObject(p);
    }
}

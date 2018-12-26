package com.tang.grpc.demo.client;

import org.springframework.stereotype.Component;

@Component
public class GrpcService {

    //这样是否支持线程安全
    private GrpcClient client;

    public GrpcService() {
        client = new GrpcClient(50001, "127.0.0.01");
    }

    public GrpcClient getClient() {
        return client;
    }
}

package com.tang.grpc.demo.client;

import org.springframework.stereotype.Component;

@Component
public class GrpcService {

    //这样是否支持线程安全
    private GrpcClient client;

    public GrpcService() {
        client = new GrpcClient("127.0.0.01", 50001);
    }

    public GrpcClient getClient() {
        return client;
    }
}

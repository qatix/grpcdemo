package com.tang.grpc.demo.client;

import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    //这样是否支持线程安全
    private HelloClient client;

    public GrpcClient() {
        client = new HelloClient(50001, "127.0.0.01");
    }

    public HelloClient getClient() {
        return client;
    }
}

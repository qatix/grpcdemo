package com.tang.grpc.demo.client;

import com.google.protobuf.ByteString;
import com.tang.grpc.demo.GreeterGrpc;
import com.tang.grpc.demo.HelloReply;
import com.tang.grpc.demo.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class HelloClient {
    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    //初始化信道和存根
    public HelloClient(int port, String host) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    private HelloClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutDown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    //客户端方法
    public String greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC调用失败：" + e.getMessage());
            return null;
        }

        System.out.println("服务器返回信息：" + response.getMessage());
        return response.getMessage();
    }

    public String greetWhat(String name, String sex, String data) {
        HelloRequest request = HelloRequest.newBuilder().setName(name)
                .setSex(sex).setData(ByteString.copyFromUtf8(data))
                .build();
        HelloReply reply = blockingStub.sayWhat(request);
        return reply.getMessage();
    }

    public static void main(String[] args) throws Exception {
        HelloClient client = new HelloClient(50001, "127.0.0.1");
        try {
            for (int i = 0; i < 5; i++) {
                client.greet("client world:" + i);
                Thread.sleep(3000);
            }

            System.out.println("------------------");

            for (int i = 0; i < 5; i++) {
                String replyMessage = client.greetWhat("new user-" + i, i % 2 == 0 ? "男" : "女", "data from client-" + i);
                System.out.println("response:" + replyMessage);
                Thread.sleep(2000);
            }
        } finally {
            client.shutDown();
        }

    }
}

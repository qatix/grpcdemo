package com.tang.grpc.demo.client;

import com.google.protobuf.ByteString;
import com.tang.grpc.demo.model.Model;
import com.tang.grpc.demo.service.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class GrpcClient {
    private final ManagedChannel channel;

    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    //初始化信道和存根
    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    private GrpcClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutDown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    //客户端方法
    public String greet(String name) {
        Model.HelloRequest request = Model.HelloRequest.newBuilder().setName(name).build();
        Model.HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC调用失败：" + e.getMessage());
            return null;
        }

//        System.out.println("服务器返回信息：" + response.getMessage());
        return response.getMessage();
    }

    public String greetWhat(String name, String sex, String data) {
        Model.HelloRequest request = Model.HelloRequest.newBuilder().setName(name)
                .setSex(sex).setData(ByteString.copyFromUtf8(data))
                .build();
        Model.HelloReply reply = blockingStub.sayWhat(request);
        System.out.println("points:" + reply.getPointsList());
        return reply.getMessage();
    }

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("127.0.0.1", 50001);
        try {
//            for (int i = 0; i < 5; i++) {
//                String res =  client.greet("client world:" + i);
////                Thread.sleep(3000);
//                System.out.println(res);
//            }

//            System.out.println("------------------");
//
            for (int i = 0; i < 5; i++) {
                String replyMessage = client.greetWhat("new user-" + i, i % 2 == 0 ? "男" : "女", "data from client-" + i);
                System.out.println("response:" + replyMessage);
                Thread.sleep(200);
            }
        } finally {
            client.shutDown();
        }

    }
}

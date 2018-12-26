package com.tang.grpc.demo.server;

import com.tang.grpc.demo.GreeterGrpc;
import com.tang.grpc.demo.HelloReply;
import com.tang.grpc.demo.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloServer {
    private int port = 50001;
    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build().start();
        System.out.println("service start ....");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloServer.this.stop();
                System.err.println("*** server shutdown");
            }
        });
    }


    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
//            super.sayHello(request, responseObserver);
            System.out.println("[sayHello]receive message:" + request.getName());

            //start work

            //end work

            HelloReply reply = HelloReply.newBuilder()
                    .setMessage(request.getName() + "[reply]").build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sayWhat(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            System.out.println("[sayWhat]receive message:" + request.getName()
                    + "\tsex:" + request.getSex()
                    + "\tdatas:" + new String(request.getData().toByteArray()));
            //start work

            //end work

            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("[reply]" + request.getName() + "|" +
                            request.getSex() + "|" + "data[" + new String(request.getData().toByteArray()) + "]").build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws Exception {
        HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }
}

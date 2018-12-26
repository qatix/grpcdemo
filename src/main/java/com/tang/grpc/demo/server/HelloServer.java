package com.tang.grpc.demo.server;

import com.tang.grpc.demo.model.Model;
import com.tang.grpc.demo.service.GreeterGrpc;
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
        public void sayHello(Model.HelloRequest request, StreamObserver<Model.HelloReply> responseObserver) {
//            super.sayHello(request, responseObserver);
            System.out.println("[sayHello]receive message:" + request.getName());

            //start work

            //end work

            Model.HelloReply reply = Model.HelloReply.newBuilder()
                    .setMessage(request.getName() + "[reply]").build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sayWhat(Model.HelloRequest request, StreamObserver<Model.HelloReply> responseObserver) {
            System.out.println("[sayWhat]receive message:" + request.getName()
                    + "\tsex:" + request.getSex()
                    + "\tdatas:" + new String(request.getData().toByteArray())
                    + "\ttype:" + request.getReqType()
            );
            //start work

            //end work

            Model.HelloReply reply = Model.HelloReply.newBuilder()
                    .setMessage("[reply]" + request.getName() + "|" +
                            request.getSex() + "|" + "data[" + new String(request.getData().toByteArray()) + "] type="+request.getReqType())
                    .addPoints(2).addPoints(33).addPoints(21).build();
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

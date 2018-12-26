package com.tang.grpc.demo.pool;

import com.tang.grpc.demo.client.GrpcClient;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Slf4j
public class PoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = newFixedThreadPool(20);

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                GrpcClient client = GrpcClientPool.borrowObject();
                if(client == null){
                    System.err.println("client-null");
                    return;
                }
                String msg = "msg-" + finalI;
                String res = client.greet(msg);
                String msgExpectRes = msg + "[reply]";
                if (!msgExpectRes.equals(res)) {
                    log.error("not-match:expect:" + msgExpectRes + " but get:" + res);
                } else {
                    log.info("match:" + msg);
                }
            });
        }
    }
}

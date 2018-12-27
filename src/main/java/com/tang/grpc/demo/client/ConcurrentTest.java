package com.tang.grpc.demo.client;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 测试是否会发生顺序错乱问题，20个线程，10000次请求，没有发生问题
 */
@Slf4j
public class ConcurrentTest {
    public static void main(String[] args) {
        GrpcClient client = new GrpcClient("127.0.0.1", 50001);

        ExecutorService executorService = newFixedThreadPool(20);

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executorService.submit(() -> {
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

从代码中可看出blockingUnaryCall其实调用的是fetureUnaryCall，所以gRPC客户端本质上全部是异步的。
所以线程池意义不是很大。

https://stackoverflow.com/questions/42320492/do-i-need-to-pool-managedchannel-instances-for-a-multithreaded-java-grpc-1-1-2

```
    public static <ReqT, RespT> RespT blockingUnaryCall(
          Channel channel, MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, ReqT req) {
        ThreadlessExecutor executor = new ThreadlessExecutor();
        ClientCall<ReqT, RespT> call = channel.newCall(method, callOptions.withExecutor(executor));
        try {
          ListenableFuture<RespT> responseFuture = futureUnaryCall(call, req);
          while (!responseFuture.isDone()) {
            try {
              executor.waitAndDrain();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              throw Status.CANCELLED
                  .withDescription("Call was interrupted")
                  .withCause(e)
                  .asRuntimeException();
            }
          }
          return getUnchecked(responseFuture);
        } catch (RuntimeException e) {
          throw cancelThrow(call, e);
        } catch (Error e) {
          throw cancelThrow(call, e);
        }
      }
```
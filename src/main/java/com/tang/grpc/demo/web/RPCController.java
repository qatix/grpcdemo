package com.tang.grpc.demo.web;

import com.tang.grpc.demo.client.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grpc")
public class RPCController {

    @Autowired
    private GrpcService grpcService;

    @GetMapping("/greet1")
    public String greet1(@RequestParam(name = "name") String name) {
        return grpcService.getClient().greet(name);
    }

    @GetMapping("/greet2")
    public String greet1(@RequestParam(name = "name") String name,
                         @RequestParam(name = "sex") String sex,
                         @RequestParam(name = "data") String data) {
        return grpcService.getClient().greetWhat(name, sex, data);
    }
}

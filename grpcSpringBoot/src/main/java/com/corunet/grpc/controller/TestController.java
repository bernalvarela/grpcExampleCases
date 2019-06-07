package com.corunet.grpc.controller;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.corunet.service.GreeterGrpc;
import com.corunet.service.GreeterGrpc.GreeterImplBase;
import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;

@RestController
public class TestController extends GreeterImplBase {

	@GrpcClient("local-grpc-server")
	private GreeterGrpc.GreeterBlockingStub client;

	@GetMapping("/sayHello/{executions}")
	public void sayHello(@PathVariable("executions") int executions) {
		for(int i=1; i < executions; i++) {
            /* Access a service running on the local machine on port 55555 */
            String name = "Bernal";
            System.out.println("Will try to greet " + name + " ...");
            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloReply response;
            try {
                response = client.sayHello(request);
            } catch (StatusRuntimeException e) {
                System.out.println("RPC failed: " + e.getStatus());
                return;
            }
            System.out.println("Greeting: " + response.getMessage());
        }
	}
}
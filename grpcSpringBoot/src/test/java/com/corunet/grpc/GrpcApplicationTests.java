package com.corunet.grpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.corunet.service.GreeterGrpc;
import com.corunet.service.Service.HelloRequest;

import net.devh.boot.grpc.client.inject.GrpcClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcApplicationTests {

	@GrpcClient("local-grpc-server")
	private GreeterGrpc.GreeterBlockingStub client;

	@Test
	public void contextLoads() {
		client.sayHello(HelloRequest.newBuilder().setName("name").build()).getMessage();
	}

}

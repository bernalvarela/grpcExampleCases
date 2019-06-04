package com.corunet.grpc;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lognet.springboot.grpc.context.LocalRunningGrpcPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.corunet.service.GreeterGrpc;
import com.corunet.service.Service.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcApplicationTests {

    protected ManagedChannel channel;

    @LocalRunningGrpcPort
    protected int runningPort;

	@Before
	public final void setupChannel() {
		channel = ManagedChannelBuilder
					.forAddress("localhost",runningPort)
					.usePlaintext().build();
	}

	@Test
	public void contextLoads() {
		try {
			GreeterGrpc.newFutureStub(channel)
			.sayHello(HelloRequest.newBuilder().setName("name").build())
			.get().getMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}

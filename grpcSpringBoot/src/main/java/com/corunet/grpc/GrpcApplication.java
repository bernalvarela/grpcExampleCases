package com.corunet.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.corunet.grpc.client.GrpcClient;

@SpringBootApplication
public class GrpcApplication {

    @Autowired
    private GrpcClient client;

	public static void main(String[] args) {
		SpringApplication.run(GrpcApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    for(int i=1; i<100; i++) {
            /* Access a service running on the local machine on port 50051 */
            String user = "world";
            client.greet(user);
        }
	}

}

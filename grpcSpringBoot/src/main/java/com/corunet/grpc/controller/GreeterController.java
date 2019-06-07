package com.corunet.grpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import io.micrometer.core.annotation.Timed;

@Controller
@Timed("greeter")
public class GreeterController {

	@PostMapping("/greeter/sayHello")
	public HelloReply sayHello(@RequestBody HelloRequest request) {
		System.out.println("Name in received message: " + request.getName());
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		return reply;
	}
}
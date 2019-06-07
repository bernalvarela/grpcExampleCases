package com.corunet.grpc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import io.micrometer.core.annotation.Timed;

@RestController
@Timed("greeter")
public class GreeterController {

    @PostMapping(path= "/greeter/sayHello", consumes = "application/json", produces = "application/json")
	public HelloReply sayHello(@RequestBody HelloRequest request) {
		System.out.println("Name in received message: " + request.getName());
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		return reply;
	}
}
package com.corunet.grpc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corunet.grpc.dto.HelloReplyDto;
import com.corunet.grpc.dto.HelloRequestDto;

@RestController
public class GreeterController {

    @PostMapping(path= "/greeter/sayHello", consumes = "application/json", produces = "application/json")
	public HelloReplyDto sayHello(@RequestBody HelloRequestDto request) {
		System.out.println("Name in received message: " + request.getName());
		HelloReplyDto reply = new HelloReplyDto();
		reply.setMessage("Hello " + request.getName());
		return reply;
	}
}
package com.corunet.grpc.server;

import com.corunet.service.GreeterGrpc.GreeterImplBase;
import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServer extends GreeterImplBase {

	@Override
	public void sayHello(HelloRequest request, io.grpc.stub.StreamObserver<HelloReply> responseObserver) {
		System.out.println("Name in received message: " + request.getName());
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
package com.corunet.grpc.server;

import org.lognet.springboot.grpc.GRpcService;

import com.corunet.service.GreeterGrpc.GreeterImplBase;
import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;

public class GrpcServer {

    @GRpcService(interceptors = {MonitoringServerInterceptor.class})
	public static class GreeterImpl extends GreeterImplBase {
		@Override
		public void sayHello(HelloRequest request, io.grpc.stub.StreamObserver<HelloReply> responseObserver) {
			System.out.println("Name in received message: " + request.getName());
			HelloReply reply = HelloReply.newBuilder().setMessage("Response").build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}
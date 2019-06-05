package com.corunet.grpc.client;

import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import com.corunet.service.GreeterGrpc;
import com.corunet.service.Service.HelloReply;
import com.corunet.service.Service.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.prometheus.client.CollectorRegistry;
import me.dinowernli.grpc.prometheus.MonitoringClientInterceptor;

public class GrpcClient {

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public GrpcClient(String host, int port, CollectorRegistry collectorRegistry) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS
                // to avoid
                // needing certificates.
                .usePlaintext().build(),
                collectorRegistry);
    }

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    GrpcClient(ManagedChannel channel, CollectorRegistry collectorRegistry) {
        this.channel = channel;

        MonitoringClientInterceptor monitoringInterceptor =
                MonitoringClientInterceptor.create(me.dinowernli.grpc.prometheus.Configuration.allMetrics().withCollectorRegistry(collectorRegistry));
        blockingStub = GreeterGrpc.newBlockingStub(channel).withInterceptors(monitoringInterceptor);
        
    }
    
    @PreDestroy
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public void greet(String name) {
        
        System.out.println("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            return;
        }
        System.out.println("Greeting: " + response.getMessage());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to
     * use in the greeting.
     */
//    public static void main(String[] args) throws Exception {
//        GrpcClient client = new GrpcClient("127.0.0.1", 55555);
//        try {
//             for(int i=1; i<100; i++) {
//            /* Access a service running on the local machine on port 50051 */
//            String user = "world";
//            if (args.length > 0) {
//                user = args[0]; /* Use the arg as the name to greet if provided */
//            }
//            client.greet(user);
//             }
//        } finally {
//            client.shutdown();
//        }
//    }
}
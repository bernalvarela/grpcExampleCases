package com.corunet.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corunet.grpc.client.GrpcClient;

import io.prometheus.client.CollectorRegistry;
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;

@Configuration
public class GrpcConfigurer {

    @Autowired
    private CollectorRegistry collectorRegistry;
    
    @Bean
    public GrpcClient grpcClient() {
        return new GrpcClient("127.0.0.1", 55555, collectorRegistry);
    }

    @Bean
    public MonitoringServerInterceptor monitoringServerInterceptor() {
        MonitoringServerInterceptor monitoringInterceptor = MonitoringServerInterceptor.create(me.dinowernli.grpc.prometheus.Configuration.allMetrics().withCollectorRegistry(collectorRegistry));
        return monitoringInterceptor;
    }

}
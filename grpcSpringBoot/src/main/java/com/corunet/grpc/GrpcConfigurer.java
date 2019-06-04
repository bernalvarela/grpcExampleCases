package com.corunet.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.CollectorRegistry;
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;

@Configuration
public class GrpcConfigurer {

    @Bean
    public MonitoringServerInterceptor monitoringServerInterceptor() {
     // use the provided registry 
        MonitoringServerInterceptor monitoringInterceptor =  
            MonitoringServerInterceptor.create(me.dinowernli.grpc.prometheus.Configuration.cheapMetricsOnly().withCollectorRegistry(CollectorRegistry.defaultRegistry));
        return monitoringInterceptor; 
      }
}
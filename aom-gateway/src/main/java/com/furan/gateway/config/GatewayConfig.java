package com.furan.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: GatewayConfig
 * @Author itmei
 * @Package com.furan.gateway.config
 * @Date 2025/5/28 19:22
 * @description: config
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 路由1: 所有包含/message的路径转发到18112端口
                .route("aom-message", r -> r
                        .path("/aommessage/**")
                        .uri("http://localhost:18112"))

                // 路由2: 其他所有路径转发到18111端口
                .route("aom-admin", r -> r
                        .path("/**")
                        .uri("http://localhost:18111"))

                .build();
    }
}

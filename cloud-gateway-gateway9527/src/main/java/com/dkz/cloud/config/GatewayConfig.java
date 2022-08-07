package com.dkz.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // 这种方式是不推荐的
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        Builder routes = routeLocatorBuilder.routes();
        // https://www.msn.cn/zh-cn
        return routes.route("path_route_dkz",
            r -> r.path("/zh-cn")
                .uri("https://www.msn.cn/zh-cn")).build();
    }

}

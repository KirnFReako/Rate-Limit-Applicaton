package ratelimitingApp.example.ratelimit.config;

import ratelimitingApp.example.ratelimit.filter.TokenBucketRateLimiterFilter;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
//defined how spring cloud gateway routes the incoming requests.
// client -> backend services

@Configuration
public class GatewayConfig {

    private final RateLimiterProperties rateLimiterProperties;
    private final TokenBucketRateLimiterFilter tokenBucketRateLimiterFilter;


    public GatewayConfig(RateLimiterProperties rateLimiterProperties, TokenBucketRateLimiterFilter tokenBucketRateLimiterFilter){
        this.rateLimiterProperties = rateLimiterProperties;
        this.tokenBucketRateLimiterFilter = tokenBucketRateLimiterFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("api-route", r -> r
                        .path("/api/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(tokenBucketRateLimiterFilter.apply(new TokenBucketRateLimiterFilter.Config()))
                        )
                        .uri(rateLimiterProperties.getApiServerUrl()))
                .build();
    }

}

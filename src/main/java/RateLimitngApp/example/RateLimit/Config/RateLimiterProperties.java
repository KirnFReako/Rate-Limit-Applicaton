package RateLimitngApp.example.RateLimit.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimiterProperties {
    private long capacity =10;
    private long refillRate =5;

    private String apiServerUrl = "http://localhost:8080";

    private long timeout =5000;

}

package ratelimitingApp.example.ratelimit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class RateLimiterService {
    private final RedisTokenBucketService redisTokenBucketService;


    public boolean isAllowed(String clientId) {
        return redisTokenBucketService.isAllowed(clientId);
    }

    public long getCapacity(String clientId){
        return redisTokenBucketService.getCapacity(clientId);
    }

    public  long getAvailableTokens(String clientId){
        return redisTokenBucketService.getAvailableTokens(clientId);
    }
}


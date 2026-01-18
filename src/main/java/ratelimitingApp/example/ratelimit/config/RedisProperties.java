package ratelimitingApp.example.ratelimit.config;




import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host = "localhost";
    private int port = 6379;
    private int timeout = 2000;

    // java client library for redis.. to let java application.. communicate with redis server
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // it is a connection pool for jedi where it keeps multiple connection ready tp reuse
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        return new JedisPool(poolConfig, host, port, timeout);
    }
}

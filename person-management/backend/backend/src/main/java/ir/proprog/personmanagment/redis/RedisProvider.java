package ir.proprog.personmanagment.redis;

import ir.proprog.personmanagment.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

@Component
public class RedisProvider {
    private static final Logger logger = LoggerFactory.getLogger(RedisProvider.class);
    private RedisTemplate redisTemplate;

    public RedisProvider(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, Object object, Duration duration) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String json = Utilities.convertObjectToJson(object);
        valueOperations.set(key, json, duration);
        logger.info("data with key: " + key + ", save successfully.");
    }

    public String getData(String key) {
        String object = null;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (Objects.nonNull(valueOperations.get(key))) {
            logger.info("data with key: " + key + ", fetch successfully.");
            object = (String) valueOperations.get(key);
        }
        return object;
    }

    public void deleteData(String key) {
        Boolean unlink = redisTemplate.unlink(key);
        if (Boolean.TRUE.equals(unlink)) {
            logger.info("data with key: " + key + ", delete successfully.");
        }
    }
}

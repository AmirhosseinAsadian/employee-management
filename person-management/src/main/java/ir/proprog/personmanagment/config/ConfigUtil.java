package ir.proprog.personmanagment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtil {

    @Value("${redisStatus.active}")
    private Boolean redisStatus;

    public Boolean getRedisStatus() {
        return redisStatus;
    }
}

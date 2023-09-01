package ir.proprog.firstapp.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.redis.RedisProvider;
import ir.proprog.firstapp.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonRedisRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonRedisRepository.class);
    private final RedisProvider redisProvider;

    public PersonRedisRepository(RedisProvider redisProvider) {
        this.redisProvider = redisProvider;
    }

    public Optional<PersonDTO> findByCode(String code) {
        try {
            Optional<PersonDTO> person = Optional.empty();
            String key = "PERSONE_CODE_" + code;
            Object data = redisProvider.getData(key);
            if (Objects.nonNull(data)) {
                ObjectMapper mapper = new ObjectMapper();
                person = Optional.of(mapper.readValue(data.toString(), PersonDTO.class));
            }
            return person;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String savePerson(PersonDTO person) {
        String key = "PERSONE_CODE_" + person.getCode();
        redisProvider.saveData(key, person, Duration.ofDays(1));
        return key;
    }
}

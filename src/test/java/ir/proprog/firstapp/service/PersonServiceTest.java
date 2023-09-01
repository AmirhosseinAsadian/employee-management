package ir.proprog.firstapp.service;

import ir.proprog.firstapp.domain.Person;
import ir.proprog.firstapp.domain.dto.PersonDTO;
import ir.proprog.firstapp.redis.RedisProvider;
import ir.proprog.firstapp.repository.PersonRedisRepository;
import ir.proprog.firstapp.sevice.PersonPredicate;
import ir.proprog.firstapp.sevice.PersonService;
import ir.proprog.firstapp.util.TestDataUtil;
import ir.proprog.firstapp.util.Utilities;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.lang.constant.Constable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan
public class PersonServiceTest {
    private List<Person> personList;
//
//    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRedisRepository personRedisRepository;

    @MockBean
    private RedisProvider redisProvider;

    @MockBean
    private ValueOperations valueOperations;

    @Mock(name = "redisTemplate")
    private RedisTemplate<Object, Object> redisTemplate;

    @Before
    public void init() {
        personList = TestDataUtil.getPersonList();
    }

    @Test
    public void save_person_successfully() {
        assertThat(new ArrayList<>(), hasSize(1));
    }

    @Test
    public void save_person_in_redis_successfully() {
        redisTemplate = new RedisTemplate<>();
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName("localhost");
        redisConfig.setPort(6379);
        redisTemplate.setConnectionFactory(new JedisConnectionFactory(redisConfig));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        redisProvider = new RedisProvider(redisTemplate);
        PersonDTO person = new PersonDTO();
        person.setName("Amir");
        person.setCode("22");
        person.setType("REAL");
        redisProvider.saveData("test_key", person, Duration.ofDays(1));
        Constable test_value = redisProvider.getData("test_key");
        assertEquals(person, test_value);
    }

    @Test
    public void get_From_Cache() {
        valueOperations.set("test-key", "Hi Amir!", 1);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        assertEquals(when(valueOperations.get("test-key")).thenReturn("Hi Amir!"), "Hi Amir!");
    }

    @Test
    public void filter_legal_person_successfully() {
        assertThat(personService.filterPerson(personList, person -> Objects.equals("LEGAL", person.getType())), hasSize(2));
    }

    @Test
    public void filter_legal_person_successfully_by_function() {
        Function<Person, Boolean> p1 = person -> Objects.equals("LEGAL", person.getType());
        Function<Person, Boolean> p2 = person -> Objects.equals("1401/01/01", person.getBirthDate());
        Function<Person, Boolean> f = person -> p1.apply(person) && p2.apply(person);

        Predicate<Person> predicateByType = person -> Objects.equals("REAL", person.getType());
        Predicate<Person> predicateByName = person -> Objects.equals("Ali", person.getName());
        Predicate<Person> predicateByNameAndType = predicateByType.and(predicateByName);

        PersonPredicate f3 = person -> Objects.equals("LEGAL", person.getType());

        assertThat(personService.filterPersonFunctional(personList, predicateByNameAndType), hasSize(1));
        assertThat(personService.filterPerson(personList, f3), hasSize(2));

        assertThat(personService.filterPerson(personList, (person -> f.apply(person))), hasSize(2));
        // by method reference or double column
        assertThat(personService.filterPerson(personList, f::apply), hasSize(2));

    }

    @Test
    public void success_open_optional() {
        String name = "Ali";
        Optional<String> name3 = Optional.ofNullable(null);

        assertThat(name3.orElseGet(getDefaultValue()), equalTo(""));
    }

    private Supplier<String> getDefaultValue() {
        return () -> "";
    }

    @Test
    public void success_flat_map_optional() {
        Optional<Integer> sum = generateOptionalNumber(1)
                .flatMap(n1 -> generateOptionalNumber(2).map(n2 -> n1 + n2))
                .flatMap(n12 -> generateOptionalNumber(3).map(n3 -> n3 + n12));

        assertThat(sum.orElse(0), equalTo(6));
    }

    private Optional<Integer> generateOptionalNumber(int a) {
        return Optional.of(a);
    }

}

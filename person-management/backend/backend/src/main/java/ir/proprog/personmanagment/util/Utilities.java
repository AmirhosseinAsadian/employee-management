package ir.proprog.personmanagment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class Utilities {

    public static String convertObjectToJson(Object object) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> convertJsonToObject(String json, Object object) {
        try {
            Class<?> o = object.getClass();
            ObjectMapper mapper = new ObjectMapper();
            o = (Class<?>) mapper.readValue(json, o);
            return o;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

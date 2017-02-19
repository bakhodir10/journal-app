package uz.com.platform.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.stereotype.Component;

@Component("hibernateAwareObjectMapper")
public class HibernateAwareObjectMapper extends ObjectMapper {
    public HibernateAwareObjectMapper() {
        Hibernate5Module module = new Hibernate5Module();
        module.configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
        this.registerModule(module);
        this.enable(SerializationFeature.INDENT_OUTPUT);
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}

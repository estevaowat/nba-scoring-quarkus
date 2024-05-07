package org.ewcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.arc.DefaultBean;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperConfig {

    @DefaultBean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
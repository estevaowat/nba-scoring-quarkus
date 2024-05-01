package org.ewcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.quarkus.arc.DefaultBean;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperConfig {

    @DefaultBean
    public ObjectMapper getObjectMapper() {
        var mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        return mapper;

    }
}
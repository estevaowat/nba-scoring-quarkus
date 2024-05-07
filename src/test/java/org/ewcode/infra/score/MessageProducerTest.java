package org.ewcode.infra.score;

import org.ewcode.ObjectMapperConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class MessageProducerTest {

    @Test
    @DisplayName("when generate messages should return size of 100")
    void generateMessages() {
        MessageProducer messageProducer = new MessageProducer(new ObjectMapperConfig().getObjectMapper());
        int size = 100;
        List<String> messages = messageProducer.generateMessages("123", size);
        Assertions.assertEquals(size, messages.size());
    }
}
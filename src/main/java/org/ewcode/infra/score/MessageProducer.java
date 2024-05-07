package org.ewcode.infra.score;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.ewcode.infra.score.dto.ScoreDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@ApplicationScoped
public class MessageProducer {

    private final ObjectMapper objectMapper;

    public MessageProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public List<String> generateMessages(String transactionId, int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> {
                    try {
                        ScoreDTO score = new ScoreDTO(i, 2, 1, 0, LocalDateTime.now(), transactionId);
                        return objectMapper.writeValueAsString(score);
                    } catch(JsonProcessingException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
